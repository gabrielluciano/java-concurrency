package com.gabrielluciano.concurrency.threads;

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from thread 2");
    }
}

public class Main {

    public static void main(String[] args) {
        // We should prefer this method which uses Runnable instead of extending the Thread class
        Thread thread1 = new Thread(() -> System.out.println("Hello from thread 1"));
        thread1.start();

        Thread thread2 = new MyThread();
        thread2.start();
    }
}
