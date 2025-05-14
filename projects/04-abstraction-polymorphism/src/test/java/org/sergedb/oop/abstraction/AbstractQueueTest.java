package org.sergedb.oop.abstraction;

import org.junit.Test;
import org.sergedb.oop.abstraction.queue.Queue;

import static org.junit.Assert.*;

public abstract class AbstractQueueTest {
    protected abstract Queue<String> createQueue();

    @Test
    public void testEnqueueDequeueOrder() {
        Queue<String> queue = createQueue();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
    }

    @Test
    public void testIsEmptyBeforeAndAfter() {
        Queue<String> queue = createQueue();
        assertTrue(queue.isEmpty());
        queue.enqueue("X");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeekDoesNotRemove() {
        Queue<String> queue = createQueue();
        queue.enqueue("peek");
        assertEquals("peek", queue.peek());
        assertEquals("peek", queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testDequeueFromEmpty() {
        Queue<String> queue = createQueue();
        assertThrows(Exception.class, queue::dequeue);
    }

    @Test
    public void testSizeIsCorrect() {
        Queue<String> queue = createQueue();
        assertEquals(0, queue.size());
        queue.enqueue("1");
        queue.enqueue("2");
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
    }
}
