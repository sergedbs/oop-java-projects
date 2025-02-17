package org.sergedb.oop.abstraction.services;

public class PeopleDinner implements Dineable {
    public void serveDinner(String carId) {
        System.out.println("Serving people in car " + carId);
    }
}
