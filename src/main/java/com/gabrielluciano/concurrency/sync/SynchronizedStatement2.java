package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedStatement2 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Counter2 counter = new Counter2();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            if (i % 2 == 0) {
                tasks.add(Executors.callable(counter::inc1));
            } else {
                tasks.add(Executors.callable(counter::inc2));
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

class Counter1 {
    private long c1 = 0;
    private long c2 = 0;

    public synchronized void inc1() {
        c1++;
    }

    public synchronized void inc2() {
        c2++;
    }

    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }
}

class Counter2 {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }

    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }
}

