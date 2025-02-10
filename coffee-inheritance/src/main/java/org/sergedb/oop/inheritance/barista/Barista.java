package org.sergedb.oop.inheritance.barista;

import org.sergedb.oop.inheritance.drinks.*;
import org.sergedb.oop.inheritance.utils.Intensity;
import org.sergedb.oop.inheritance.utils.SyrupType;

public class Barista {

    public Coffee makeEspresso(Intensity intensity) {
        return new Coffee(intensity);
    }

    public Americano makeAmericano(Intensity intensity, int water) {
        return new Americano(intensity, water);
    }

    public Cappuccino makeCappuccino(Intensity intensity, int milk) {
        return new Cappuccino(intensity, milk);
    }

    public SyrupCappuccino makeSyrupCappuccino(Intensity intensity, int milk, SyrupType syrup) {
        return new SyrupCappuccino(intensity, milk, syrup);
    }

    public PumpkinSpiceLatte makePumpkinSpiceLatte(Intensity intensity, int milk, int pumpkinSpice) {
        return new PumpkinSpiceLatte(intensity, milk, pumpkinSpice);
    }



}
