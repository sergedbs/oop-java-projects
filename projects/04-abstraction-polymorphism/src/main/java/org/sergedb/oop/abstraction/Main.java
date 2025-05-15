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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        String queueDir = "/Users/sergiu/Projects/oop-java-projects/projects/04-abstraction-polymorphism/queue";

        List<CarStation> stations = new ArrayList<>();
        stations.add(new CarStation(new SimpleQueue<>(), new PeopleDinner(), new GasStation()));      // GAS + PEOPLE
        stations.add(new CarStation(new SimpleQueue<>(), new RobotDinner(), new GasStation()));       // GAS + ROBOTS
        stations.add(new CarStation(new SimpleQueue<>(), new PeopleDinner(), new ElectricStation())); // ELECTRIC + PEOPLE
        stations.add(new CarStation(new SimpleQueue<>(), new RobotDinner(), new ElectricStation()));  // ELECTRIC + ROBOTS

        Semaphore semaphore = new Semaphore(stations);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(
                new ScheduledCarReader(queueDir, semaphore),
                0, 2, TimeUnit.SECONDS
        );

        executor.scheduleAtFixedRate(
                new ScheduledCarServer(semaphore),
                1, 3, TimeUnit.SECONDS
        );

    }
}
