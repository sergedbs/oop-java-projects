package org.sergedb.oop.inheritance.coffee;

public class Coffee {

    protected String coffeeName;
    protected Intensity coffeIntensity;

    public Coffee(String name, Intensity intensity) {
        this.coffeeName = name;
        this.coffeIntensity = intensity;
    }

    public Coffee() {
        this("Espresso", Intensity.STRONG);
    }
}
