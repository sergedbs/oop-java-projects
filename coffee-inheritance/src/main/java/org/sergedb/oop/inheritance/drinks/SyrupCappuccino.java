package org.sergedb.oop.inheritance.drinks;

import org.sergedb.oop.inheritance.utils.Intensity;
import org.sergedb.oop.inheritance.utils.SyrupType;

class SyrupCappuccino extends Cappuccino {
    protected SyrupType syrup;

    public SyrupCappuccino(Intensity intensity, int milk, SyrupType syrupType) {
        super("Cappuccino with Syrup", intensity, milk);
        this.syrup = syrupType;
    }

    public SyrupCappuccino(SyrupType syrupType) {
        this(Intensity.NORMAL, 150, syrupType);
    }

    public String coffeeDetails() {
        return super.coffeeDetails() + "\nAdding " + syrup + " syrup.";
    }
}
