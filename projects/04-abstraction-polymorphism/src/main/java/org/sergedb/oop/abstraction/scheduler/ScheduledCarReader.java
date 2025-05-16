package org.sergedb.oop.abstraction.scheduler;

import org.sergedb.oop.abstraction.dispatcher.Semaphore;
import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.utils.LogBuffer;
import org.sergedb.oop.common.utils.FileUtils;
import org.sergedb.oop.common.utils.JsonUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ScheduledCarReader implements Runnable {

    private final String queueDir;
    private final Semaphore semaphore;
    private final LogBuffer logBuffer;
    private final CountDownLatch doneLatch;

    private long lastActivity = System.currentTimeMillis();
    private static final long INACTIVITY_TIMEOUT_MS = 10_000; // 10 seconds

    public ScheduledCarReader(String queueDir, Semaphore semaphore, LogBuffer logBuffer, CountDownLatch doneLatch) {
        this.queueDir = queueDir;
        this.semaphore = semaphore;
        this.logBuffer = logBuffer;
        this.doneLatch = doneLatch;
    }

    @Override
    public void run() {
        boolean didWork = false;
        List<Path> jsonFiles = FileUtils.getFilesFromDirectory(queueDir, ".json");

        for (Path filePath : jsonFiles) {
            didWork = true;
            String file = filePath.toString();
            List<Car> cars = JsonUtils.readFromJson(file, Car.class, null);

            for (Car car : cars) {
                try {
                    semaphore.addCar(car);
                    logBuffer.logf("[READER] Car %s dispatched.", car.id());
                } catch (Exception e) {
                    System.err.println("[READER] Failed to dispatch car: " + car.id() + " - " + e.getMessage());
                }
            }

            try {
                Files.delete(filePath);
            } catch (IOException e) {
                System.err.println("[READER] Could not delete file: " + file + " - " + e.getMessage());
            }
        }

        if (didWork) {
            lastActivity = System.currentTimeMillis();
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastActivity > INACTIVITY_TIMEOUT_MS) {
                logBuffer.logf("[SERVER] No new car for %d ms. Shutting down.", INACTIVITY_TIMEOUT_MS);
                doneLatch.countDown();
            }
        }
    }
}

