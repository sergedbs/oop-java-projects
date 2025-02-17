package org.sergedb.oop.common.ui;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import java.io.IOException;

/**
 * Abstract base class for JLine-based UI components with animated text.
 * Provides common methods for terminal output, text animations, and screen handling.
 */
public abstract class BaseAnimatedUI {
    protected final Terminal terminal;
    protected final String text;
    protected volatile boolean runningAnimation = true;

    /**
     * Initializes the terminal and text for the UI component.
     * @param text The text to display and animate.
     * @throws IOException If terminal initialization fails.
     */
    protected BaseAnimatedUI(String text) throws IOException {
        this.terminal = TerminalBuilder.builder().system(true).build();
        this.text = text;
    }

    /**
     * Animates text appearing character by character with multi-line support.
     */
    protected void animateText() {
        String[] lines = text.split("\n");
        int linesCount = lines.length;
        StringBuilder animatedText = new StringBuilder("\n");
        for (char c : text.toCharArray()) {
            if (!runningAnimation) return;
            animatedText.append(c);
            for (int i = 0; i < linesCount; i++) terminal.writer().print("\033[A");
            terminal.writer().print("\r\u001B[0m" + animatedText + " ");
            terminal.writer().flush();
            try { Thread.sleep(30); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
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
     * Renders a header text in the terminal.
     */
    protected void renderHeader() {
        clearScreen();
        terminal.writer().println("\n" + text + "\n");
    }

    /**
     * Closes the terminal.
     */
    protected void closeTerminal() {
        try { terminal.close(); } catch (IOException e) { e.printStackTrace(); }
    }
}
