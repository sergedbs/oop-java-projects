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

    public Cappuccino(Intensity intensity, int milk) {
        this("Cappuccino", intensity, milk);
    }

    public String toString() {
        return super.toString() + "\nAdding " + mlOfMilk + "ml of milk.";
    }
}
