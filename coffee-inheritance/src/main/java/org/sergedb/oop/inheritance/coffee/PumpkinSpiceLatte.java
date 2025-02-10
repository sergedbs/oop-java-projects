package org.sergedb.oop.inheritance.coffee;

public class PumpkinSpiceLatte extends Cappuccino {
    protected int mgOfPumpkinSpice;

    public PumpkinSpiceLatte(Intensity intensity, int milk, int pumpkinSpice) {
        super("Pumpkin Spice Latte", intensity, milk);
        this.mgOfPumpkinSpice = pumpkinSpice;
    }

    public PumpkinSpiceLatte() {
        this(Intensity.LIGHT, 250, 25);
    }
}
