package org.sergedb.oop.abstraction.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRefuelable implements Refuelable {
    protected final String fuelType;
    private int count = 0;
    private final List<String> refueledCars = new ArrayList<>();

    public AbstractRefuelable(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void refuel(String carId) {
        count++;
        refueledCars.add(carId);
        System.out.println("Refueling " + fuelType + " car " + carId);
    }

    public int getRefueledCount() {
        return count;
    }

    public List<String> getRefueledCars() {
        return Collections.unmodifiableList(refueledCars);
    }
}

