package com.gabrielluciano.concurrency.threads;


public class Sleep {

    public static void main(String[] args) throws InterruptedException {
        String[] messages = { "Hello,", "How", "Are", "You?" };
        for (int i = 0; i < messages.length; i++) {
            System.out.print(messages[i] + " ");
            Thread.sleep(1000);
        }
    }
}
