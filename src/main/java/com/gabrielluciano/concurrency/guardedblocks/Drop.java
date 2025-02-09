package com.gabrielluciano.concurrency.guardedblocks;

public class Drop {

    // Message sent from producer to consumer
    private String message;

    // True if consumer should wait for producer to send a message
    // False if producer should wait for consumer to retrieve message
    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            try {
                // wait() will release the acquired lock for the calling thread
                // and suspend execution until its notified or interrupted
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        empty = true;
        notifyAll();

        return message;
    }

    public synchronized void put(String message) {
        while (!empty) {
            try {
                // wait() will release the acquired lock for the calling thread
                // and suspend execution until its notified or interrupted
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        empty = false;
        this.message = message;

        notifyAll();
    }
}
