package org.sergedb.oop.abstraction.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDineable implements Dineable {
    protected final String passengerType;
    private int count = 0;
    private final List<String> servedCars = new ArrayList<>();

    public AbstractDineable(String passengerType) {
        this.passengerType = passengerType;
    }

    @Override
    public void serveDinner(String carId) {
        count++;
        servedCars.add(carId);
        System.out.println("Serving dinner to " + passengerType + " in car " + carId);
    }

    public int getServedCount() {
        return count;
    }

    public List<String> getServedCars() {
        return Collections.unmodifiableList(servedCars);
    }
}
