package org.sergedb.oop.inheritance;

import org.sergedb.oop.inheritance.drinks.PumpkinSpiceLatte;
import org.sergedb.oop.inheritance.utils.Intensity;
import org.sergedb.oop.inheritance.utils.MenuSelector;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PumpkinSpiceLatte pumpkinSpiceLatte = new PumpkinSpiceLatte(Intensity.NORMAL, 150, 25);
        System.out.println(pumpkinSpiceLatte);
    }
}
