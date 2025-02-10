package org.sergedb.oop.inheritance.barista;

import java.util.Scanner;

public class BaristaManager {

    String baristaName;
    Barista barista;

    public BaristaManager(String name) {
        this.baristaName = name;
        barista = new Barista();
    }

    public int showDrinkOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                What would you like to have today?:
                1. Espresso
                2. Americano
                3. Cappuccino
                5. Pumpkin Spice Latte
                6. I changed my mind, I will leave.
                """);
        return scanner.nextInt();
    }

    public int showIntensityOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                What intensity would you like?:
                1. Light
                2. Normal
                3. Strong
                """);
        return scanner.nextInt();
    }

    public int showWaterOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much water would you like? (in ml): ");
        return scanner.nextInt();
    }

    public int showMilkOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much milk would you like? (in ml): ");
        return scanner.nextInt();
    }

    public int showPumpkinSpiceOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much pumpkin spice would you like? (in mg): ");
        return scanner.nextInt();
    }

    public int showSyrupOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                What syrup would you like?:
                1. Vanilla
                2. Caramel
                3. Chocolate
                4. Coconut
                5. Popcorn
                6. Macadamia
                0. None
                """);
        return scanner.nextInt();
    }

    public boolean customizeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to customize your order? (y/n): ");
        String choice = scanner.nextLine();
        return choice.equals("y");
    }

    public void showMenu() {
        System.out.println("Hello, I am " + baristaName + ".\n");
        while(true) {
            int choice = showDrinkOptions();
            int intensityChoice = showIntensityOptions();

            switch (choice) {
                case 1:
                    //barista.makeEspresso();
                    break;
                case 2:
                    System.out.println("Here is your Syrup Cappuccino.");
                    break;
                case 3:
                    System.out.println("Here is your Americano.");
                    break;
                case 4:
                    System.out.println("Here is your Pumpkin Spice Latte.");
                    break;
                default:
                    System.out.println("Sorry, we do not have that.");
                    break;
            }
        }
    }
}
