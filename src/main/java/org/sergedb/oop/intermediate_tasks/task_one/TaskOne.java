package org.sergedb.oop.intermediate_tasks.task_one;

public class TaskOne {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager();

        Display lgDisplay = new Display(2560, 1440, 127.7f, "LG 1440p 23 inch");
        Display samsungDisplay = new Display(3840, 2160, 163.18f, "Samsung 4K 27 inch");
        Display dellDisplay = new Display(1920, 1080, 157.35f, "Dell FHD 14 inch");

        displayManager.addDisplay("LG", lgDisplay);
        displayManager.addDisplay("Samsung", samsungDisplay);
        displayManager.addDisplay("Dell", dellDisplay);

        ComparisonService.compareDisplays(lgDisplay, samsungDisplay);
        ComparisonService.compareDisplays(dellDisplay, lgDisplay);
        ComparisonService.compareDisplays(samsungDisplay, dellDisplay);
    }
}
