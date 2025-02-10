package org.sergedb.oop.inheritance.drinks;

import org.sergedb.oop.inheritance.utils.Intensity;

public class PumpkinSpiceLatte extends Cappuccino {
    protected int mgOfPumpkinSpice;

    public PumpkinSpiceLatte(Intensity intensity, int milk, int pumpkinSpice) {
        super("Pumpkin Spice Latte", intensity, milk);
        this.mgOfPumpkinSpice = pumpkinSpice;
    }

    public PumpkinSpiceLatte() {
        this(Intensity.LIGHT, 250, 25);
    }

    public String coffeeDetails() {
        return super.coffeeDetails() + "\nAdding " + mgOfPumpkinSpice + "mg of pumpkin spice.";
    }
}
