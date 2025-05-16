package org.sergedb.oop.abstraction.scheduler;

import java.util.concurrent.CountDownLatch;

import org.sergedb.oop.abstraction.dispatcher.Semaphore;
import org.sergedb.oop.abstraction.utils.LogBuffer;

public class ScheduledCarServer implements Runnable {

    private final Semaphore semaphore;
    private final LogBuffer logBuffer;
    private final CountDownLatch doneLatch;
    private int tick = 0;

    private long lastActivity = System.currentTimeMillis();
    private static final long INACTIVITY_TIMEOUT_MS = 10_000; // 10 seconds

    public ScheduledCarServer(Semaphore semaphore, LogBuffer logBuffer, CountDownLatch doneLatch) {
        this.semaphore = semaphore;
        this.logBuffer = logBuffer;
        this.doneLatch = doneLatch;
    }

    @Override
    public void run() {
        tick++;
        boolean didWork = semaphore.serveAll(logBuffer);
        logBuffer.flush(tick);

        if (didWork) {
            lastActivity = System.currentTimeMillis();
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastActivity > INACTIVITY_TIMEOUT_MS) {
                logBuffer.logf("[SERVER] No activity for %d ms. Shutting down.", INACTIVITY_TIMEOUT_MS);
                doneLatch.countDown();
            }
        }
    }
}
