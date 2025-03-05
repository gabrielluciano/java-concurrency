package com.gabrielluciano.concurrency.highlevel.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

class VirtualThreadExecutor implements Executor {

    private static final Thread.Builder virtualThreadBuilder = Thread.ofVirtual();

    @Override
    public void execute(Runnable command) {
        virtualThreadBuilder.start(command);
    }
}

public class ExecutorExample {

    public static void main(String[] args) throws InterruptedException {
        var executor = new VirtualThreadExecutor();
        var latch = new CountDownLatch(1);
        executor.execute(() -> { 
            System.out.println("Running on thread: " + Thread.currentThread());
            latch.countDown();;
        });
        latch.await(10, TimeUnit.SECONDS);
    }
}
