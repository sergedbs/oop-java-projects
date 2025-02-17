package org.sergedb.oop.common.ui;

import java.io.IOException;

/**
 * UI component for displaying animated information with JLine and waiting for user acknowledgment.
 */
public class DisplayScreenUI extends BaseAnimatedUI {

    /**
     * Constructs a DisplayInfo instance with a given message.
     * @param message The message to display.
     * @throws IOException If terminal initialization fails.
     */
    public DisplayScreenUI(String message) throws IOException {
        super(message);
    }

    /**
     * Displays the animated message and waits for the user to press SPACE to continue.
     * @throws IOException If an issue occurs reading input.
     */
    public void waitForSpace() throws IOException {
        try {
            terminal.enterRawMode();
            clearScreen();
            animateText();
            terminal.writer().println("\n\u001B[36m[ Press SPACE to continue ]");
            terminal.writer().flush();

            while (true) {
                int key = terminal.reader().read();
                if (key == 32) break; // Space key
            }
        } finally {
            clearScreen();
            closeTerminal();
        }
    }
}