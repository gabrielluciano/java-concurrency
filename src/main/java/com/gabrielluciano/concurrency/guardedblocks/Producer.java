package com.gabrielluciano.concurrency.guardedblocks;

import java.util.Random;

public class Producer implements Runnable {

    private final Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        final String[] importantInfo = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        final Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        drop.put("DONE");
    }
}
