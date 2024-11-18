package org.sergedb.oop.classes.tasks.displayassistant;

import org.sergedb.oop.classes.tasks.display.Display;
import org.sergedb.oop.classes.tasks.display.DisplayComparator;

import java.util.ArrayList;
import java.util.List;

public class Assistant {
    private final String assistantName;
    private final List<Display> assignedDisplays;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        this.assignedDisplays = new ArrayList<>();
    }

    public String getAssistantName() {
        return assistantName;
    }

    public List<Display> getAssignedDisplays() {
        return assignedDisplays;
    }

    public void assignDisplay(Display display) {
        assignedDisplays.add(display);
    }

    public Display removeDisplay(Display display) {
        return assignedDisplays.remove(display) ? display : null;
    }
}
