package org.sergedb.oop.common.ui;

import java.io.IOException;

/**
 * UI component for capturing user text input using JLine with animated prompts.
 */
public class TextInputUI extends BaseAnimatedUI {

    /**
     * Constructs a UserTextInput with a given animated message.
     *
     * @param message The message to display before user input.
     * @throws IOException If terminal initialization fails.
     */
    public TextInputUI(String message) throws IOException {
        super(message);
    }

    /**
     * Displays the animated message and waits for user input.
     *
     * @return The user input as a trimmed string.
     * @throws IOException If an issue occurs reading input.
     */
    public String getUserInput() throws IOException {
        try {
            terminal.enterRawMode();
            clearScreen();
            terminal.writer().println();
            animateText();
            return readUserInput();
        } finally {
            clearScreen();
            closeTerminal();
        }
    }

    private String readUserInput() throws IOException {
        StringBuilder input = new StringBuilder();
        while (true) {
            int key = terminal.reader().read();
            if (key == 10 || key == 13) break; // Enter key
            else if (key == 8 || key == 127) { // Backspace key
                if (!input.isEmpty()) {
                    input.deleteCharAt(input.length() - 1);
                    terminal.writer().print("\b \b");
                    terminal.writer().flush();
                }
            } else {
                input.append((char) key);
                terminal.writer().print((char) key);
                terminal.writer().flush();
            }
        }
        return input.toString().trim();
    }
}