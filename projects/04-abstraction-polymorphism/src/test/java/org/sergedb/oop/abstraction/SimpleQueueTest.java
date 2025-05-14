package org.sergedb.oop.abstraction;

import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.queue.SimpleQueue;

public class SimpleQueueTest extends AbstractQueueTest {
    @Override
    protected Queue<String> createQueue() {
        return new SimpleQueue<>();
    }
}

