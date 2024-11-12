package org.sergedb.oop.intermediate_tasks.task_three;

import org.sergedb.oop.intermediate_tasks.task_one.ComparisonService;
import org.sergedb.oop.intermediate_tasks.task_one.Display;

import java.util.List;

public class Assistant {
    private final String assistantName;
    private List<Display> assignedDisplays;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
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

    public Display buyDisplay(Display display) {
        assignedDisplays.remove(display);
        return display;
    }

    public void assist() {
        for (int i = 0; i < assignedDisplays.size() - 1; i++) {
            Display currentDisplay = assignedDisplays.get(i);
            Display nextDisplay = assignedDisplays.get(i + 1);
            ComparisonService.compareDisplays(currentDisplay, nextDisplay);
        }
    }

}
