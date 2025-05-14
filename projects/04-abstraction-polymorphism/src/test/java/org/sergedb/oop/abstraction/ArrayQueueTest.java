package org.sergedb.oop.abstraction;

import org.sergedb.oop.abstraction.queue.ArrayQueue;
import org.sergedb.oop.abstraction.queue.Queue;

public class ArrayQueueTest extends AbstractQueueTest {
    @Override
    protected Queue<String> createQueue() {
        return new ArrayQueue<>();
    }
}

