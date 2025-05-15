package org.sergedb.oop.abstraction.dispatcher;

import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.station.CarStation;
import org.sergedb.oop.abstraction.utils.LogBuffer;

import java.util.List;

public class Semaphore {
    private final List<CarStation> stations;

    public Semaphore(List<CarStation> stations) {
        this.stations = stations;
    }

    public void addCar(Car car) {
        for (CarStation station : stations) {
            if (station.canServe(car)) {
                station.addCar(car);
                return;
            }
        }
        throw new IllegalArgumentException("No matching CarStation found for car: " + car.id());
    }

    public void serveAll(LogBuffer logBuffer) {
        for (CarStation station : stations) {
            station.serveCars(logBuffer);
        }
    }
}
