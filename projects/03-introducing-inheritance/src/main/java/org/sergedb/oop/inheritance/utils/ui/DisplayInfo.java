package org.sergedb.oop.inheritance.utils.ui;

import java.io.IOException;

public class DisplayInfo extends AbstractAnimatedUI {

    /**
     * Constructs a DisplayInfo instance with a default message.
     *
     * @throws IOException If an issue occurs initializing the terminal.
     */
    public DisplayInfo(String message) throws IOException {
        super(message);
    }

    /**
     * Displays the animated message first and then waits for the user to press SPACE to continue.
     *
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
