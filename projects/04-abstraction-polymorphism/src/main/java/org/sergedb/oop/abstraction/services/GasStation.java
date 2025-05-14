package org.sergedb.oop.abstraction.services;

public class GasStation implements Refuelable {

    @Override
    public void refuel(String carId) {
        System.out.println("Refueling gas car " + carId + ". ");
    }
}
