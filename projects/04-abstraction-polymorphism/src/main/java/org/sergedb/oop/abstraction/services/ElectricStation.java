package org.sergedb.oop.abstraction.services;

public class ElectricStation implements Refuelable {
    public void refuel(String carId) {
        System.out.println("Charging electric car " + carId + ". ");
    }
}
