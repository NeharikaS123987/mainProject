package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    private final List<Double> data = new ArrayList<>();

    public void addMoistureData(double value) {
        data.add(value);
    }

    public List<Double> getMoistureData() {
        return data;
    }

    public double getLatestValue() {
        return data.isEmpty() ? 0 : data.get(data.size() - 1);
    }
}
