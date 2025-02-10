package org.sergedb.oop.inheritance.coffee;

public class Cappuccino extends Coffee{
    protected int mlOfMilk;
    protected String name;

    public Cappuccino(String name, Intensity intensity, int milk) {
        super(name, intensity);
        this.name = name;
        this.mlOfMilk = milk;
    }

    public Cappuccino() {
        this("Cappuccino", Intensity.NORMAL, 150);
    }
}
