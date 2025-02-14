package com.gabrielluciano.concurrency.immutableobjects;

final class ImmutableRGB {

    private final int red;
    private final int green;
    private final int blue;
    private final String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    private ImmutableRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public static ImmutableRGB create(int red, int green, int blue, String name) {
        return new ImmutableRGB(red, green, blue, name);
    }

    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name);
    }

    @Override
    public String toString() {
        return "ImmutableRGB [red=" + red + ", green=" + green + ", blue=" + blue + ", name=" + name + "]";
    }
}

public class ImmutableRGBTest {
    public static void main(String[] args) {
        ImmutableRGB color = ImmutableRGB.create(255, 0, 0, "red");

        new Thread(() -> {
            System.out.println(color + " Ref: " + System.identityHashCode(color));
            ImmutableRGB invert = color.invert();
            System.out.println(invert + " Ref: " + System.identityHashCode(invert));
        }).start();
    }
}

