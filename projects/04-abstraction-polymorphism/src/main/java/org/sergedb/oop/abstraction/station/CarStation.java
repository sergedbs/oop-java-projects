package org.sergedb.oop.abstraction.station;

import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.services.Dineable;
import org.sergedb.oop.abstraction.services.Refuelable;

public class CarStation {
    private final Queue<Car> queue;
    private final Dineable diningService;
    private final Refuelable refuelingService;

    public CarStation(Queue<Car> queue, Dineable diningService, Refuelable refuelingService) {
        this.queue = queue;
        this.diningService = diningService;
        this.refuelingService = refuelingService;
    }

    public void addCar(Car car) {
        queue.enqueue(car);
    }

    public void serveCars() {
    }
}
