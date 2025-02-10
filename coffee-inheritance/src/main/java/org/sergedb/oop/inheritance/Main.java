package org.sergedb.oop.inheritance;

import org.sergedb.oop.inheritance.drinks.PumpkinSpiceLatte;

public class Main {
    public static void main(String[] args) {
        PumpkinSpiceLatte myPumpkinSpiceLatte = new PumpkinSpiceLatte();
        System.out.println(myPumpkinSpiceLatte.coffeeDetails());
    }
}
