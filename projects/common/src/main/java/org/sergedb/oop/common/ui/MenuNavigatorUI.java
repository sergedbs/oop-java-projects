package org.sergedb.oop.common.ui;

import java.io.IOException;
import java.util.List;

/**
 * Menu selector component using JLine for terminal-based interactive menus.
 */
public class MenuNavigatorUI extends BaseAnimatedUI {
    private final List<String> options;
    private int selectedIndex = 0;

    /**
     * Constructs a MenuSelector with given title text and options.
     * @param menuText The title text.
     * @param options The list of menu options.
     * @throws IOException If an issue occurs initializing the terminal.
     */
    public MenuNavigatorUI(String menuText, List<String> options) throws IOException {
        super(menuText);
        this.options = List.copyOf(options);
    }

    /**
     * Displays the menu and allows user selection using arrow keys.
     * @return The selected menu option.
     * @throws IOException If an issue occurs reading input.
     */
    public String selectOption() throws IOException {
        try {
            terminal.enterRawMode();
            clearScreen();
            animateText();
            while (true) {
                renderMenu();
                int key = terminal.reader().read();
                if (isEscapeSequence(key)) handleArrowKeyInput();
                else if (isEnterKey(key)) {
                    runningAnimation = false;
                    return options.get(selectedIndex);
                }
            }
        } finally {
            clearScreen();
            closeTerminal();
        }
    }

    private void renderMenu() {
        renderHeader();
        for (int i = 0; i < options.size(); i++) {
            terminal.writer().println((i == selectedIndex ? "> [32m" : "  ") + options.get(i) + "[0m");
        }
        terminal.writer().flush();
    }

    private void handleArrowKeyInput() throws IOException {
        int next1 = terminal.reader().read();
        int next2 = terminal.reader().read();
        if (next1 == 91) {
            switch (next2) {
                case 65 -> moveSelectionUp();
                case 66 -> moveSelectionDown();
            }
        }
    }

    private void moveSelectionUp() { selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : options.size() - 1; }
    private void moveSelectionDown() { selectedIndex = (selectedIndex < options.size() - 1) ? selectedIndex + 1 : 0; }
    private boolean isEscapeSequence(int key) { return key == 27; }
    private boolean isEnterKey(int key) { return key == 10 || key == 13; }
}
