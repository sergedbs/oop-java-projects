package org.sergedb.oop.inheritance;

import org.sergedb.oop.inheritance.barista.Barista;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Barista barista = new Barista("Zonea");
        barista.showMenu();
    }
}
