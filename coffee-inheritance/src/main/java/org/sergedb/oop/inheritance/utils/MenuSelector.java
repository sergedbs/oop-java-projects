package org.sergedb.oop.inheritance.utils;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.util.List;

public class MenuSelector {
    private final Terminal terminal;
    private final List<String> options;
    private final String menuText;
    private int selectedIndex = 0;

    /**
     * Constructor for MenuSelector.
     * @param menuText The title text for the menu.
     * @param options List of options to display.
     * @throws IOException If there is an issue initializing the terminal.
     */
    public MenuSelector(String menuText, List<String> options) throws IOException {
        this.terminal = TerminalBuilder.builder().system(true).build();
        this.menuText = menuText;
        this.options = options;
    }

    /**
     * Displays the menu and allows selection using arrow keys.
     * @return The selected menu option.
     */
    public String selectOption() throws IOException {
        try {
            terminal.enterRawMode(); // Enable raw mode for immediate key detection
            clearScreen();

            while (true) {
                renderMenu();
                int key = terminal.reader().read(); // Read single key press

                if (key == 27) { // Escape sequence (Arrow keys start with ESC)
                    int next1 = terminal.reader().read();
                    int next2 = terminal.reader().read();

                    if (next1 == 91) { // Arrow keys: ESC [
                        switch (next2) {
                            case 65: // Up Arrow
                                moveSelectionUp();
                                break;
                            case 66: // Down Arrow
                                moveSelectionDown();
                                break;
                        }
                    }
                } else if (key == 10 || key == 13) { // Enter Key (10 = LF, 13 = CR)
                    return options.get(selectedIndex);
                }
            }
        } finally {
            closeTerminal();
        }
    }

    /**
     * Renders the menu dynamically with updated selection.
     */
    private void renderMenu() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();

        terminal.writer().println("\n" + menuText + "\n");

        for (int i = 0; i < options.size(); i++) {
            if (i == selectedIndex) {
                terminal.writer().println("> \u001B[32m" + options.get(i) + "\u001B[0m"); // Highlight green
            } else {
                terminal.writer().println("  " + options.get(i));
            }
        }
        terminal.writer().flush();
    }

    /**
     * Clears the screen to ensure smooth UI refresh.
     */
    private void clearScreen() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
    }

    /**
     * Moves selection up in the menu.
     */
    private void moveSelectionUp() {
        selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : options.size() - 1;
    }

    /**
     * Moves selection down in the menu.
     */
    private void moveSelectionDown() {
        selectedIndex = (selectedIndex < options.size() - 1) ? selectedIndex + 1 : 0;
    }

    /**
     * Closes the terminal resource.
     */
    private void closeTerminal() {
        try {
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}