package org.sergedb.oop.inheritance.coffee;

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
}
