package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PlotGraph {
    private final XYSeries series = new XYSeries("Moisture %");
    private int x = 0;

    public PlotGraph(DataCollection data, MoistureCalculator calc) {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Soil Moisture", "Time (s)", "Moisture %", dataset, PlotOrientation.VERTICAL, true, true, false);

        JFrame frame = new JFrame("Live Moisture Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.setSize(600, 400);
        frame.setVisible(true);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double val = calc.convertToPercentage(data.getLatestValue());
                series.add(x++, val);
            }
        }, 0, 1000);
    }
}
