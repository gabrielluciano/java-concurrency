package com.gabrielluciano.concurrency.sync;

import java.util.ArrayList;
import java.util.List;

class Purchase {
    private static List<Purchase> instances = new ArrayList<>();

    private String product;

    Purchase(String product) {
        instances.add(this);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.product = product;
    }

    private Purchase() {
    }

    public String getProduct() {
        return product;
    }

    public static List<Purchase> getInstances() {
        return instances;
    }
}

public class MemoryInconsistency {

    public static void main(String[] args) throws InterruptedException {
        Thread purchaseCreator = new Thread(() -> {
            var purchase1 = new Purchase("car");
            var purchase2 = new Purchase("bike");
            var purchase3 = new Purchase("motorcycle");
            var purchase4 = new Purchase("notebook");
            var purchase5 = new Purchase("mouse");
        });

        Thread purchaseVerifier = new Thread(() -> {
            // Accessing a reference of an object that haven't finish creating yet
            System.out.println(Purchase.getInstances().get(0).getProduct());
        });

        purchaseCreator.start();
        purchaseVerifier.start();
        purchaseVerifier.join();
    }
}
