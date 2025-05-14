package org.sergedb.oop.abstraction.queue;

public interface Queue<T> {
    void enqueue(T item);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();
}
