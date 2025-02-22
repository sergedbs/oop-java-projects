package org.sergedb.oop.inheritance.coffee.drinks;

import org.sergedb.oop.inheritance.coffee.Intensity;

public class PumpkinSpiceLatte extends Cappuccino {
    protected int mgOfPumpkinSpice;

    public PumpkinSpiceLatte(Intensity intensity, int milk, int pumpkinSpice) {
        super("Pumpkin Spice Latte", intensity, milk);
        this.mgOfPumpkinSpice = pumpkinSpice;
    }

    public String toString() {
        return super.toString() + "\nAdding " + mgOfPumpkinSpice + "mg of pumpkin spice.";
    }
}
