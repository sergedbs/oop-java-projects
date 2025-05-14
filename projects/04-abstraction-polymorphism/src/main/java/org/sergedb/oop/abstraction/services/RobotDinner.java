package org.sergedb.oop.abstraction.services;

public class RobotDinner implements Dineable {
    public void serveDinner(String carId) {
        System.out.println("Serving robots in car " + carId + ". ");
    }
}
