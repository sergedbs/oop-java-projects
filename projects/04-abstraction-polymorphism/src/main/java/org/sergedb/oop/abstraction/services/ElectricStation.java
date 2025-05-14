package org.sergedb.oop.abstraction.services;

public class ElectricStation implements Refuelable {

    @Override
    public void refuel(String carId) {
        System.out.println("Charging electric car " + carId + ". ");
    }
}
