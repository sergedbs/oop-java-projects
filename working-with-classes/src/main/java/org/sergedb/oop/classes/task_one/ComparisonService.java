package org.sergedb.oop.classes.task_one;

import java.util.Map;

public class ComparisonService {

        public static void compareDisplays(Display display1, Display display2) {
        Map<String, Integer> comparisonResults = display1.compareWithMonitor(display2);

        int sizeComparison = comparisonResults.get("sizeComparison");
        int sharpnessComparison = comparisonResults.get("sharpnessComparison");

        if (sizeComparison == 0 && sharpnessComparison == 0) {
            System.out.println(display1.getModel() + " is the same size and sharpness as " + display2.getModel());
        } else if (sizeComparison == 0 && sharpnessComparison > 0) {
            System.out.println(display1.getModel() + " is sharper than " + display2.getModel());
        } else if (sizeComparison == 0 && sharpnessComparison < 0) {
            System.out.println(display1.getModel() + " is less sharp than " + display2.getModel());
        } else if (sizeComparison > 0 && sharpnessComparison == 0) {
            System.out.println(display1.getModel() + " is larger than " + display2.getModel());
        } else if (sizeComparison < 0 && sharpnessComparison == 0) {
            System.out.println(display1.getModel() + " is smaller than " + display2.getModel());
        } else if (sizeComparison > 0 && sharpnessComparison > 0) {
            System.out.println(display1.getModel() + " is larger and sharper than " + display2.getModel());
        } else if (sizeComparison < 0 && sharpnessComparison < 0) {
            System.out.println(display1.getModel() + " is smaller and less sharp than " + display2.getModel());
        } else if (sizeComparison > 0 && sharpnessComparison < 0) {
            System.out.println(display1.getModel() + " is larger, but less sharp than " + display2.getModel());
        } else if (sizeComparison < 0 && sharpnessComparison > 0) {
            System.out.println(display1.getModel() + " is smaller, but sharper than " + display2.getModel());
        }
    }
}
