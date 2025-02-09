package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedStatement1 {

    public static void main(String[] args) throws InterruptedException {
        final int taskCount = 1000;
        testUnsafeCounter2(taskCount);
        testSafeCounter2(taskCount);
    }

    private static void testUnsafeCounter2(int taskCount) throws InterruptedException {
        UnsafeCounter2 unsafeCounter = new UnsafeCounter2();
        ExecutorService executor1 = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= taskCount; i++) {
            tasks.add(Executors.callable(unsafeCounter::increment));
        }

        executor1.invokeAll(tasks);

        System.out.println("Unsafe Counter Result: " + unsafeCounter.getCount());
    }

    private static void testSafeCounter2(int taskCount) throws InterruptedException {
        SafeCounter2 safeCounter = new SafeCounter2();
        ExecutorService executor1 = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 1; i <= taskCount; i++) {
            tasks.add(Executors.callable(safeCounter::increment));
        }

        executor1.invokeAll(tasks);

        System.out.println("Safe Counter Result: " + safeCounter.getCount());
    }
}

class UnsafeCounter2 {

    private int count;

    public void increment() { count ++; }
    public int getCount() { return count; }
}

class SafeCounter2 {

    private int count;

    public void increment() { 
        synchronized(this) {
            count ++;
        }
    }
    public int getCount() { return count; }
}
