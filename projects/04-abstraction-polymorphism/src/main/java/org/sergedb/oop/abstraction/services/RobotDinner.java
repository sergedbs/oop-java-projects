package org.sergedb.oop.abstraction.services;

public class RobotDinner implements Dineable {

    @Override
    public void serveDinner(String carId) {
        System.out.println("Serving robots in car " + carId + ". ");
    }
}
