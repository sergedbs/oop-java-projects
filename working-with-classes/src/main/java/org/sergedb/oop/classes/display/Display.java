package org.sergedb.oop.classes.display;

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

}
