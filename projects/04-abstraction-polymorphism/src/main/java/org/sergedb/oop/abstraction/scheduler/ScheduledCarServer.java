package org.sergedb.oop.abstraction.scheduler;

import org.sergedb.oop.abstraction.dispatcher.Semaphore;

public class ScheduledCarServer implements Runnable {

    private final Semaphore semaphore;
    private int tick = 0;

    public ScheduledCarServer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        tick++;
        System.out.println("[TICK #" + tick + "] Serving all cars in all stations...");
        semaphore.serveAll();
    }
}
