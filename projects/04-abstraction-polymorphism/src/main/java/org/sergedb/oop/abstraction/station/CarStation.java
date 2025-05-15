package org.sergedb.oop.abstraction.station;

import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.services.*;
import org.sergedb.oop.abstraction.utils.LogBuffer;

public class CarStation {
    private final String name;
    private final Queue<Car> queue;
    private final Dineable diningService;
    private final Refuelable refuelingService;

    public CarStation(String name, Queue<Car> queue, Dineable diningService, Refuelable refuelingService) {
        this.name = name;
        this.queue = queue;
        this.diningService = diningService;
        this.refuelingService = refuelingService;
    }

    public void addCar(Car car) {
        queue.enqueue(car);
    }

    public void serveCars(LogBuffer logBuffer) {
        while (!queue.isEmpty()) {
            Car car = queue.dequeue();

            boolean dined = false;
            boolean refueled = false;

            if (car.isDining()) {
                diningService.serveDinner(car.id());
                dined = true;
            }

            refuelingService.refuel(car.id());
            refueled = true;

            StringBuilder result = new StringBuilder();
            result.append(String.format("[SERVER] Station %s served car %s:", name, car.id()));

            if (refueled) {
                result.append(" ").append(car.type()).append(" refueled.");
            }
            if (dined) {
                result.append(" ").append(car.passengers()).append(" dinned.");
            }

            logBuffer.log(result.toString());
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
