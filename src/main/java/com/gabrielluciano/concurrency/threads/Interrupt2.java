package com.gabrielluciano.concurrency.threads;

public class Interrupt2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
                for (int i = 0; i < numbers.length; i++) {
                    System.out.println(i + ": " + numbers[i]);

                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
        thread.interrupt();

        Thread.sleep(1000);

        System.out.println("Thread está interrompida? " + thread.isInterrupted());
    }
}
