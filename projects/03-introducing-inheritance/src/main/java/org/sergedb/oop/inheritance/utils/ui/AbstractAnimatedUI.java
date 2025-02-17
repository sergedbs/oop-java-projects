package org.sergedb.oop.inheritance.utils.ui;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;

/**
 * Abstract base class for UI components with animated text.
 * Provides common methods for handling terminal output and animations.
 */
abstract class AbstractAnimatedUI {
    protected final Terminal terminal;
    protected final String text;
    protected volatile boolean runningAnimation = true;

    /**
     * Constructor to initialize terminal and text.
     *
     * @param text The text to animate.
     * @throws IOException If an issue occurs initializing the terminal.
     */
    protected AbstractAnimatedUI(String text) throws IOException {
        this.terminal = TerminalBuilder.builder().system(true).build();
        this.text = text;
    }

/**
 * Animates text appearing character by character, handling multi-line input correctly.
 */
protected void animateText() {
    String[] lines = text.split("\n");
    int linesCount = lines.length;

    StringBuilder animatedText = new StringBuilder("\n");
    for (char c : text.toCharArray()) {
        if (!runningAnimation) return;

        animatedText.append(c);

        // Move the cursor up before printing new frame (for multi-line support)
        for (int i = 0; i < linesCount; i++) {
            terminal.writer().print("\033[A");
        }

        terminal.writer().print("\r\u001B[0m" + animatedText + " ");
        terminal.writer().flush();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    terminal.writer().println();
}


    /**
     * Clears the terminal screen.
     */
    protected void clearScreen() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
    }

    /**
     * Renders the common text header for UI components.
     */
    protected void renderHeader() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        terminal.writer().println("\n" + text + "\n");
    }

    /**
     * Closes the terminal resource.
     */
    protected void closeTerminal() {
        try {
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}