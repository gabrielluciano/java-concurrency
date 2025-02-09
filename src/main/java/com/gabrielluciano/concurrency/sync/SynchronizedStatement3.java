package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedStatement3 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Counter counter = new Counter();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                tasks.add(Executors.callable(counter::incrementC1));
            } else {
                tasks.add(Executors.callable(counter::incrementC2));
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.invokeAll(tasks);
        executorService.shutdown();

        System.out.println(counter.getC1());
        System.out.println(counter.getC2());
        System.out.println("Duration: " + (System.currentTimeMillis() - startTime));
    }
}

class Counter {
    private int c1 = 0;
    private int c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void incrementC1() {
        synchronized(lock1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c1++;
        }
    }

    public void incrementC2() {
        synchronized(lock2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c2++;
        }
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }
}

