package org.sergedb.oop.inheritance.drinks;

import org.sergedb.oop.inheritance.utils.Intensity;

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

    public String coffeeDetails() {
        return super.coffeeDetails() + "\nAdding " + mlOfMilk + "ml of milk.";
    }
}
