package org.example;

public class MoistureCalculator {
    private final double max = 740;
    private final double min = 495;

    public double convertToPercentage(double raw) {
        if (raw > max) return 0;
        if (raw < min) return 100;
        return ((max - raw) / (max - min)) * 100.0;
    }
}
