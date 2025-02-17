package org.sergedb.oop.inheritance.utils.ui;

import java.io.IOException;

public class UserTextInput extends AbstractAnimatedUI {
    public UserTextInput(String message) throws IOException {
        super(message);
    }

    /**
     * Displays the animated message and waits for user input.
     *
     * @return The user input as a string.
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

        if (key == 10 || key == 13) { // Enter key
            break;
        } else if (key == 8 || key == 127) { // Backspace key
            if (!input.isEmpty()) {
                input.deleteCharAt(input.length() - 1);
                terminal.writer().print("\b \b"); // Move cursor back, erase character, move cursor back
                terminal.writer().flush();
            }
        } else {
            input.append((char) key);
            terminal.writer().print((char) key); // Display character as user types
            terminal.writer().flush();
        }
    }
    return input.toString().trim();
}

}
