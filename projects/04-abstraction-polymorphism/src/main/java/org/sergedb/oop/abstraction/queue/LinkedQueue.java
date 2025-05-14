package org.sergedb.oop.abstraction.queue;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head = null;  // front of queue
    private Node<T> tail = null;  // end of queue
    private int size = 0;

    @Override
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        T value = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }


    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
