package ru.training.karaf.model;

public class ColorImpl implements Color {

    private int red;
    private int green;
    private int blue;
    private int alpha;

    public ColorImpl(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public ColorImpl() {
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int getAlpha() {
        return alpha;
    }
}
