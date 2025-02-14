package com.gabrielluciano.concurrency.immutableobjects;

public class SynchronizedRGB {

    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public synchronized void set(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public static void main(String[] args) {
        SynchronizedRGB color = new SynchronizedRGB(255, 0, 0, "red");

        // Bad code
        new Thread(() -> {
            int myColorInt = color.getRGB();
            String myColorName = color.getName();
            System.out.println("" + myColorInt + " " + myColorName);
        }).start();

        // Good code
        new Thread(() -> {
            synchronized(color) {
                int myColorInt = color.getRGB();
                String myColorName = color.getName();
                System.out.println("" + myColorInt + " " + myColorName);
            }
        }).start();
    }
}
