package org.sergedb.oop.inheritance.coffee;

class SyrupCappuccino extends Cappuccino {
    protected SyrupType syrup;

    public SyrupCappuccino(Intensity intensity, int milk, SyrupType syrupType) {
        super("Cappuccino with Syrup", intensity, milk);
        this.syrup = syrupType;
    }

    public SyrupCappuccino(SyrupType syrupType) {
        this(Intensity.NORMAL, 150, syrupType);
    }
}
