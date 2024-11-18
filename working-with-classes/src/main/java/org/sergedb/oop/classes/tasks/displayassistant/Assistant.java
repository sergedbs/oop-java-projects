package org.sergedb.oop.classes.tasks.displayassistant;

import org.sergedb.oop.classes.tasks.display.Display;

import java.util.ArrayList;
import java.util.List;

public class Assistant {
    private final List<Display> assignedDisplays;

    public Assistant() {
        this.assignedDisplays = new ArrayList<>();
    }

    public List<Display> getAssignedDisplays() {
        return assignedDisplays;
    }

    public void assignDisplay(Display display) {
        assignedDisplays.add(display);
    }

    public boolean removeDisplay(Display display) {
        return assignedDisplays.remove(display);
    }
}
