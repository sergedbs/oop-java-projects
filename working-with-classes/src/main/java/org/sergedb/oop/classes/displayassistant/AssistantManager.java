package org.sergedb.oop.classes.displayassistant;

import org.sergedb.oop.classes.display.Display;

import java.util.HashMap;
import java.util.Map;

public class AssistantManager {
    private final Map<String, Display> displayList = new HashMap<>();

    public void addDisplay(String model, Display display) {
        displayList.put(model, display);
    }

    public Display getDisplay(String model) {
        return displayList.get(model);
    }
}
