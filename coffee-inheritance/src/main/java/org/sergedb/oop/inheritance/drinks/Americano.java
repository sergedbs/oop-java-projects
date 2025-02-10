package org.sergedb.oop.inheritance.drinks;

import org.sergedb.oop.inheritance.utils.Intensity;

public class Americano extends Coffee{
    protected int mlOfWater;
    protected String name;

    public Americano(String name, Intensity intensity, int water) {
        super(name, intensity);
        this.name = name;
        this.mlOfWater = water;
    }

    public Americano() {
        this("Americano", Intensity.NORMAL, 150);
    }

    public String coffeeDetails() {
        return super.coffeeDetails() + "\nAdding " + mlOfWater + "ml of water.";
    }
}
