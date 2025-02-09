package com.gabrielluciano.concurrency.threads;

class Reader implements Runnable {

    final String[] messages = { "Hello,", "How", "Are", "You?" };

    @Override
    public void run() {
        for (int i = 0; i < messages.length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.print(messages[i] + " ");
        }
    }
}

public class Interrupt1 {

    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(new Reader());
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();
    }
}
