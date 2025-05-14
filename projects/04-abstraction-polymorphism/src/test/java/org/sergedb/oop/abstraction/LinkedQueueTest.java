package org.sergedb.oop.abstraction;

import org.sergedb.oop.abstraction.queue.LinkedQueue;
import org.sergedb.oop.abstraction.queue.Queue;

public class LinkedQueueTest extends AbstractQueueTest {
    @Override
    protected Queue<String> createQueue() {
        return new LinkedQueue<>();
    }
}

