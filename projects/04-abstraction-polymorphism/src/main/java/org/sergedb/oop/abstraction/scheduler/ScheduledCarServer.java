package org.sergedb.oop.abstraction.scheduler;

import org.sergedb.oop.abstraction.dispatcher.Semaphore;
import org.sergedb.oop.abstraction.utils.LogBuffer;

public class ScheduledCarServer implements Runnable {

    private final Semaphore semaphore;
    private final LogBuffer logBuffer;
    private int tick = 0;

    public ScheduledCarServer(Semaphore semaphore, LogBuffer logBuffer) {
        this.semaphore = semaphore;
        this.logBuffer = logBuffer;
    }

    @Override
    public void run() {
        tick++;
        semaphore.serveAll(logBuffer);
        logBuffer.flush(tick);
    }
}
