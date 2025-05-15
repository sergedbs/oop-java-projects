package org.sergedb.oop.abstraction.station;

import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.services.*;

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
        while (!queue.isEmpty()) {
            Car car = queue.dequeue();

            if (car.isDining()) {
                diningService.serveDinner(car.id());
            }

            refuelingService.refuel(car.id());
        }
    }

    public boolean canServe(Car car) {
        boolean fuelMatches =
                (refuelingService instanceof GasStation && car.type().equals("GAS")) ||
                        (refuelingService instanceof ElectricStation && car.type().equals("ELECTRIC"));

        boolean passengerMatches =
                (diningService instanceof PeopleDinner && car.passengers().equals("PEOPLE")) ||
                        (diningService instanceof RobotDinner && car.passengers().equals("ROBOTS"));

        return fuelMatches && passengerMatches;
    }

}
