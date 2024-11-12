package org.sergedb.oop.intermediate_tasks.task_one;

import java.util.Map;

public class TaskOne {
    public static void main(String[] args) {
        Display lgDisplay = new Display(2560, 1440, 127.7f, "LG 1440p 23 inch");
        Display samsungDisplay = new Display(3840, 2160, 163.18f, "Samsung 4K 27 inch");
        Display dellDisplay = new Display(1920, 1080, 157.35f, "Dell FHD 14 inch");

        compareDisplays(lgDisplay, samsungDisplay);
        compareDisplays(dellDisplay, lgDisplay);
        compareDisplays(samsungDisplay, dellDisplay);
    }

    private static void compareDisplays(Display display1, Display display2) {
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
