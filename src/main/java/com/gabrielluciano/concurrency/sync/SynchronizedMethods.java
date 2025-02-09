package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UnsafeCounter {

    private int count;

    public void increment() { count ++; }
    public int getCount() { return count; }
}

class SafeCounter {

    private int count;

    public synchronized void increment() { count ++; }
    public int getCount() { return count; }
}

public class SynchronizedMethods {

    public static void main(String[] args) throws InterruptedException {
        final int taskCount = 1000;
        testUnsafeCounter(taskCount);
        testSafeCounter(taskCount);
    }

    private static void testUnsafeCounter(int taskCount) throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        ExecutorService executor1 = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= taskCount; i++) {
            tasks.add(Executors.callable(unsafeCounter::increment));
        }

        executor1.invokeAll(tasks);

        System.out.println("Unsafe Counter Result: " + unsafeCounter.getCount());
    }

    private static void testSafeCounter(int taskCount) throws InterruptedException {
        SafeCounter safeCounter = new SafeCounter();
        ExecutorService executor1 = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= taskCount; i++) {
            tasks.add(Executors.callable(safeCounter::increment));
        }

        executor1.invokeAll(tasks);

        System.out.println("Safe Counter Result: " + safeCounter.getCount());
    }
}
