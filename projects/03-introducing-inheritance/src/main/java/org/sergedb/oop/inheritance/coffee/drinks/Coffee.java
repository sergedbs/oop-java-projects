package org.sergedb.oop.inheritance.coffee.drinks;

import org.sergedb.oop.inheritance.coffee.Intensity;

public class Coffee {

    protected String coffeeName;
    protected Intensity coffeIntensity;

    public Coffee(String name, Intensity intensity) {
        this.coffeeName = name;
        this.coffeIntensity = intensity;
    }

    public Coffee(Intensity intensity) {
        this("Espresso", intensity);
    }

    public String toString() {
        return "Making your " + coffeeName + ".\n"
        + "Setting Intensity to " + coffeIntensity + ".";
    }
}
