package org.sergedb.oop.abstraction.utils;

import java.util.ArrayList;
import java.util.List;

public class LogBuffer {
    private final List<String> messages = new ArrayList<>();

    public synchronized void log(String msg) {
        messages.add(msg);
    }

    public synchronized void logf(String format, Object... args) {
        messages.add(String.format(format, args));
    }

    public synchronized boolean isEmpty() {
        return messages.isEmpty();
    }

    public synchronized void flush(int tick) {
        if (isEmpty()) {
            System.out.printf("[TICK #%d] No cars processed.\n", tick);
            return;
        }

        System.out.printf("\n[TICK #%d] New car(s) processed.\n", tick);
        messages.forEach(System.out::println);
        System.out.println("--------------------------------------------------\n");
        messages.clear();
    }
}

