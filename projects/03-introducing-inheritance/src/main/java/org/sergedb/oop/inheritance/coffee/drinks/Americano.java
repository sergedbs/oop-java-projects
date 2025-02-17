package org.sergedb.oop.inheritance.coffee.drinks;

import org.sergedb.oop.inheritance.coffee.Intensity;

public class Americano extends Coffee {
    protected int mlOfWater;
    protected String name;

    public Americano(Intensity intensity, int water) {
        super("Americano", intensity);
        this.mlOfWater = water;
    }

    public String toString() {
        return super.toString() + "\nAdding " + mlOfWater + "ml of water.";
    }
}
