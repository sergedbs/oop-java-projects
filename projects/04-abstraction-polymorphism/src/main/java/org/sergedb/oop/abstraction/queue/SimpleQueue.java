package org.sergedb.oop.abstraction.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Queue<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public void enqueue(T item) {
        items.add(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return items.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return items.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public int size() {
        return items.size();
    }
}
