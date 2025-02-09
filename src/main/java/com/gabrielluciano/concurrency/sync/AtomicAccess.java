package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Numbers {
    public long number = 0;
}

public class AtomicAccess {

    public static void main(String[] args) throws InterruptedException {
        Numbers numbers = new Numbers();
        final long iterations = 10000;

        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            final int newValue = i;
            tasks.add(Executors.callable(() -> {
                numbers.number = newValue + 1;
                System.out.printf("New value: %d, numbers.number: %d%n", newValue, numbers.number);
            }));
        }

        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        executorService.invokeAll(tasks);
        executorService.shutdown();
    }
}

