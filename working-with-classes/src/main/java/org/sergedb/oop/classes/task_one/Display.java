package org.sergedb.oop.classes.task_one;

import java.util.HashMap;
import java.util.Map;

public class Display {
    private final int width;
    private final int height;
    private final float ppi;
    private final String model;

    public Display(int width, int height, float ppi, String model) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getPpi() {
        return ppi;
    }

    public String getModel() {
        return model;
    }

    public int compareSize(Display display) {
        int thisSize = this.width * this.height;
        int otherSize = display.width * display.height;

        return Integer.compare(thisSize, otherSize);
    }

    public int compareSharpness(Display display) {
        return Float.compare(this.ppi, display.ppi);
    }

    public Map<String, Integer> compareWithMonitor(Display display) {
        int sizeComparison = compareSize(display);
        int sharpnessComparison = compareSharpness(display);

        Map<String, Integer> comparisonResults = new HashMap<>();
        comparisonResults.put("sizeComparison", sizeComparison);
        comparisonResults.put("sharpnessComparison", sharpnessComparison);

    return comparisonResults;
    }

}
