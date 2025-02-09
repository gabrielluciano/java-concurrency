package com.gabrielluciano.concurrency.threads;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("índice: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
        thread.join(); // Wait until it finishes

        System.out.println("Thread finished! Proceeding");
    }
}
