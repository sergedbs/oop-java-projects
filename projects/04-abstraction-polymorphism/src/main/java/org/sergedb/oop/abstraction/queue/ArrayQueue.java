package org.sergedb.oop.abstraction.queue;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public ArrayQueue() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(T item) {
        if (size == data.length) {
            resize();
        }
        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        T item = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return data[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }
}
