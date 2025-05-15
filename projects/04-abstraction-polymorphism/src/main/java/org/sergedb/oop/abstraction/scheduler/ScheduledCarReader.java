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

public class ScheduledCarReader implements Runnable {

    private final String queueDir;
    private final Semaphore semaphore;
    private final LogBuffer logBuffer;

    public ScheduledCarReader(String queueDir, Semaphore semaphore, LogBuffer logBuffer) {
        this.queueDir = queueDir;
        this.semaphore = semaphore;
        this.logBuffer = logBuffer;
    }

    @Override
    public void run() {
        List<Path> jsonFiles = FileUtils.getFilesFromDirectory(queueDir, ".json");

        for (Path filePath : jsonFiles) {
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
    }
}

