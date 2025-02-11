package org.sergedb.oop.inheritance.barista;

import org.sergedb.oop.inheritance.utils.ui.MenuSelector;
import org.sergedb.oop.inheritance.utils.ui.DisplayInfo;
import org.sergedb.oop.inheritance.coffee.Intensity;
import org.sergedb.oop.inheritance.coffee.SyrupType;

import java.io.IOException;
import java.util.List;

public class Barista {

    private final String baristaName;
    private final CoffeeMaker coffeeMaker;

    public Barista(String name) {
        this.baristaName = name;
        this.coffeeMaker = new CoffeeMaker();
    }

    /**
     * Displays a menu and returns the selected option index.
     *
     * @param prompt The prompt text for the menu.
     * @param options The list of menu options.
     * @return The index of the selected option (1-based index).
     * @throws IOException If an issue occurs while reading input.
     */
    private int showMenuOptions(String prompt, List<String> options) throws IOException {
        MenuSelector menu = new MenuSelector(prompt, options);
        return options.indexOf(menu.selectOption()) + 1;
    }

    public int showDrinkOptions() throws IOException {
        return showMenuOptions("- What would you like to have today?", List.of(
                "Espresso", "Americano", "Cappuccino", "Cappuccino with Syrup", "Pumpkin Spice Latte", "I changed my mind, I will leave."));
    }

    public Intensity showIntensityOptions() throws IOException {
        int choice = showMenuOptions("- What intensity would you like?", List.of( "Normal", "Light", "Strong"));
        return switch (choice) {
            case 2 -> Intensity.DECAF;
            case 3 -> Intensity.DOUBLE;
            default -> Intensity.NORMAL;
        };
    }

    public SyrupType showSyrupOptions() throws IOException {
        int choice = showMenuOptions("- What syrup would you like?", List.of(
                "Vanilla", "Caramel", "Chocolate", "Coconut", "Popcorn", "Macadamia"));
        return switch (choice) {
            case 1 -> SyrupType.VANILLA;
            case 2 -> SyrupType.CARAMEL;
            case 3 -> SyrupType.CHOCOLATE;
            case 4 -> SyrupType.COCONUT;
            case 5 -> SyrupType.POPCORN;
            case 6 -> SyrupType.MACADAMIA;
            default -> null;
        };
    }

    public boolean customizeOrder() throws IOException {
        return showMenuOptions("- Would you like to customize your order?", List.of("Yes", "No")) == 1;
    }

    public int showWaterOptions() throws IOException {
        return switch (showMenuOptions("- How much water would you like?", List.of("Normal (100ml)", "Short (50ml)", "Long (150ml)"))) {
            case 2 -> 50;
            case 3 -> 150;
            default -> 100;
        };
    }

    public int showMilkOptions() throws IOException {
        return switch (showMenuOptions("- How much milk would you like?", List.of("Normal (100ml)", "Half (50ml)", "Double (200ml)"))) {
            case 2 -> 50;
            case 3 -> 200;
            default -> 100;
        };
    }

    public int showPumpkinSpiceOptions() throws IOException {
        return switch (showMenuOptions("- How much pumpkin spice would you like?", List.of("2 Tablespoons (Normal)", "1 Tablespoon", "3 Tablespoons"))) {
            case 2 -> 15;
            case 3 -> 45;
            default -> 30;
        };
    }

    /**
     * Displays a message and waits for user input before continuing.
     *
     * @param message The message to be displayed.
     * @throws IOException If an issue occurs while displaying the message.
     */
    private void displayMessage(String message) throws IOException {
        new DisplayInfo(message).waitForSpace();
    }

    /**
     * Displays the main barista menu and handles drink preparation.
     */
    public void showMenu() throws IOException {
        displayMessage("- Hi! Welcome to the coffee shop. \nI am " + baristaName + ", and I will be your barista today.");

        while (true) {
            try {
                int choice = showDrinkOptions();
                if (choice == 6) {
                    displayMessage("- Goodbye! Have a great day!");
                    break;
                }

                SyrupType syrup = (choice == 4) ? showSyrupOptions() : null;
                boolean customOrder = customizeOrder();
                Intensity intensity = customOrder ? showIntensityOptions() : Intensity.NORMAL;
                int water = (choice == 2 && customOrder) ? showWaterOptions() : 100;
                int milk = ((choice == 3 || choice == 4 || choice == 5) && customOrder) ? showMilkOptions() : 100;
                int pumpkinSpice = (choice == 5 && customOrder) ? showPumpkinSpiceOptions() : 30;

                switch (choice) {
                    case 1 -> displayMessage(coffeeMaker.makeEspresso(intensity).toString());
                    case 2 -> displayMessage(coffeeMaker.makeAmericano(intensity, water).toString());
                    case 3 -> displayMessage(coffeeMaker.makeCappuccino(intensity, milk).toString());
                    case 4 -> displayMessage(coffeeMaker.makeSyrupCappuccino(intensity, milk, syrup).toString());
                    case 5 -> displayMessage(coffeeMaker.makePumpkinSpiceLatte(intensity, milk, pumpkinSpice).toString());
                }

                displayMessage("- Your drink is ready! Enjoy!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
