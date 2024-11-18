package org.sergedb.oop.classes.tasks.displayassistant;

import org.sergedb.oop.classes.tasks.display.Display;
import org.sergedb.oop.classes.tasks.display.DisplayComparator;

import java.util.*;

public class AssistantManager {
    private final List<Display> displayList;
    private final Assistant assistant;
    private final String assistantName;

    public AssistantManager(List<Display> displayList, String assistantName) {
        this.displayList = displayList;
        this.assistant = new Assistant(assistantName);
        this.assistantName = assistantName;
    }

    public void showAvailableDisplays() {
        System.out.println(assistantName + ": Hi! These are our available displays:");
        int index = 1;
        for (Display display : displayList) {
            System.out.println(index++ + ". " + display.model() + " (ID: " + display.id() + ")");
        }
    }

    public void showAssignedDisplays() {
        List<Display> assignedDisplays = assistant.getAssignedDisplays();
        if (assignedDisplays.isEmpty()) {
            System.out.println(assistantName + ": Oh no! You have NOT selected any displays. :(");
        } else {
            System.out.println(assistantName + ": Here are your selected displays:");
            int index = 1;
            for (Display display : assignedDisplays) {
                System.out.println(index++ + ". " + display.model() + " (ID: " + display.id() + ")");
            }
        }
    }

    public void assignDisplays(List<Integer> ids) {
        for (Integer id : ids) {
            Display display = displayList.stream()
                                         .filter(d -> d.id() == id)
                                         .findFirst()
                                         .orElse(null);
            if (display == null) {
                System.out.println(assistantName + ": Oh no! We do NOT have this display (ID: " + id + ") in stock");
            } else if (assistant.getAssignedDisplays().contains(display)) {
                System.out.println(assistantName + ": You silly, you already have selected this display (ID: " + id + ")");
            } else {
                assistant.assignDisplay(display);
                System.out.println(assistantName + ": Great choice! Display " + display.model() + " (ID: " + id + ") added with success to your cart.");
            }
        }
    }

    public void buyDisplay(int id) {
        Display display = displayList.stream()
                                     .filter(d -> d.id() == id)
                                     .findFirst()
                                     .orElse(null);
        if (display == null || !assistant.getAssignedDisplays().contains(display)) {
            System.out.println(assistantName + ": Oh no! This display (ID: " + id + ") does not exist or is not added to your cart.");
        } else {
            assistant.removeDisplay(display);
            System.out.println("Great choice! You bought a shiny, brand new" + display.model() +  " (ID: " + id + "). Enjoy it!");
        }
    }

    public void compareDisplays() {
        DisplayComparator displayComparator = new DisplayComparator();
        for (int i = 0; i < assistant.getAssignedDisplays().size() - 1; i++) {
            Display currentDisplay = assistant.getAssignedDisplays().get(i);
            Display nextDisplay = assistant.getAssignedDisplays().get(i + 1);
            System.out.println(displayComparator.compareTwoDisplays(currentDisplay, nextDisplay));
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + assistantName + ": Welcome to our displays store! Bellow are your options:");
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("A(ssign) B(uy) C(ompare) S(show) E(xit)");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    showAvailableDisplays();
                    System.out.println("Enter display IDs to assign (comma-separated):");
                    String[] idsToAssign = scanner.nextLine().split(",");
                    List<Integer> idListToAssign = new ArrayList<>();
                    for (String id : idsToAssign) {
                        try {
                            idListToAssign.add(Integer.parseInt(id.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID format: " + id);
                        }
                    }
                    assignDisplays(idListToAssign);
                    break;
                case "B":
                    showAssignedDisplays();
                    System.out.println("Enter display ID to buy:");
                    String idToBuy = scanner.nextLine().trim();
                    try {
                        buyDisplay(Integer.parseInt(idToBuy));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + idToBuy);
                    }
                    break;
                case "C":
                    compareDisplays();
                    break;
                case "S":
                    showAssignedDisplays();
                    break;
                case "E":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}