package org.sergedb.oop.inheritance.drinks;

import org.sergedb.oop.inheritance.utils.Intensity;
import org.sergedb.oop.inheritance.utils.SyrupType;

public class SyrupCappuccino extends Cappuccino {
    protected SyrupType syrup;

    public SyrupCappuccino(Intensity intensity, int milk, SyrupType syrupType) {
        super("Cappuccino with Syrup", intensity, milk);
        this.syrup = syrupType;
    }

    public String toString() {
        return super.toString() + "\nAdding " + syrup + " syrup.";
    }
}
