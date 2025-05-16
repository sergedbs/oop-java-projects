package org.sergedb.oop.abstraction;

import org.sergedb.oop.abstraction.dispatcher.Semaphore;
import org.sergedb.oop.abstraction.queue.SimpleQueue;
import org.sergedb.oop.abstraction.scheduler.ScheduledCarReader;
import org.sergedb.oop.abstraction.scheduler.ScheduledCarServer;
import org.sergedb.oop.abstraction.services.ElectricStation;
import org.sergedb.oop.abstraction.services.GasStation;
import org.sergedb.oop.abstraction.services.PeopleDinner;
import org.sergedb.oop.abstraction.services.RobotDinner;
import org.sergedb.oop.abstraction.station.CarStation;
import org.sergedb.oop.abstraction.utils.LogBuffer;
import org.sergedb.oop.abstraction.utils.StatsTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {

        LogBuffer logBuffer = new LogBuffer();

        String queueDir = "/Users/sergiu/Projects/oop-java-projects/projects/04-abstraction-polymorphism/queue";

        StatsTrack statsTrack = new StatsTrack();
        List<CarStation> stations = createStations(statsTrack);
        Semaphore semaphore = new Semaphore(stations);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        CountDownLatch shutdownLatch = new CountDownLatch(1);
        CountDownLatch tasksDoneLatch = new CountDownLatch(2);

        AtomicBoolean shutdownInitiated = new AtomicBoolean(false);
        Runnable shutdownAction = createShutdownAction(executor, shutdownLatch, shutdownInitiated);

        Runtime.getRuntime().addShutdownHook(new Thread(shutdownAction));

        Thread timeoutThread = new Thread(() -> {
            try {
                Thread.sleep(120_000);
                System.out.println("[MAIN] Timeout reached.");
                shutdownAction.run();
            } catch (InterruptedException ignored) {}
        });
        timeoutThread.setDaemon(true);
        timeoutThread.start();

        Thread watcherThread = new Thread(() -> {
            try {
                tasksDoneLatch.await();
                System.out.println(statsTrack);
                System.out.println("[MAIN] Both tasks finished.");
                shutdownAction.run();
            } catch (InterruptedException ignored) {}
        });
        watcherThread.setDaemon(true);
        watcherThread.start();

        try {
            executor.scheduleAtFixedRate(
                    new ScheduledCarReader(queueDir, semaphore, logBuffer, tasksDoneLatch),
                    0, 2, TimeUnit.SECONDS
            );

            executor.scheduleAtFixedRate(
                    new ScheduledCarServer(semaphore, logBuffer, tasksDoneLatch),
                    1, 3, TimeUnit.SECONDS
            );

            shutdownLatch.await();
            System.out.println("[MAIN] Executor service shut down.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[MAIN] Interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[MAIN] Error: " + e.getMessage());
        }
    }

    private static List<CarStation> createStations(StatsTrack statsTrack) {
        List<CarStation> stations = new ArrayList<>();
        stations.add(new CarStation("GP", new SimpleQueue<>(), new PeopleDinner(), new GasStation(), statsTrack));
        stations.add(new CarStation("GR", new SimpleQueue<>(), new RobotDinner(), new GasStation(), statsTrack));
        stations.add(new CarStation("EP", new SimpleQueue<>(), new PeopleDinner(), new ElectricStation(), statsTrack));
        stations.add(new CarStation("ER", new SimpleQueue<>(), new RobotDinner(), new ElectricStation(), statsTrack));
        return stations;
    }

    private static Runnable createShutdownAction(
        ScheduledExecutorService executor,
        CountDownLatch shutdownLatch,
        AtomicBoolean shutdownInitiated
    ) {
        return () -> {
            if (shutdownInitiated.compareAndSet(false, true)) {
                System.out.println("[MAIN] Initiating shutdown...");
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                    Thread.currentThread().interrupt();
                }
                shutdownLatch.countDown();
            }
        };
    }
}
