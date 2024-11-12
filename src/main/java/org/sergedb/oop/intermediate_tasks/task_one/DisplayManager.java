package org.sergedb.oop.intermediate_tasks.task_one;

import java.util.HashMap;
import java.util.Map;

public class DisplayManager {
    private final Map<String, Display> displayList = new HashMap<>();

    public void addDisplay(String model, Display display) {
        displayList.put(model, display);
    }

    public Display getDisplay(String model) {
        return displayList.get(model);
    }
}
