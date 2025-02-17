package org.sergedb.oop.abstraction.queue;

import java.util.ArrayList;
import java.util.List;

public class SimpleQueue<T> implements Queue<T> {
    private final List<T> items = new ArrayList<>();

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue() {
        return isEmpty() ? null : items.removeFirst();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
