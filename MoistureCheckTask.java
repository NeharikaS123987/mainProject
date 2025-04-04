package org.example;

import org.firmata4j.Pin;

import java.util.TimerTask;

public class MoistureCheckTask extends TimerTask {
    private final Pin sensor;
    private final Pin pump;
    private final PumpControl pumpControl;
    private final DataCollection dataLog;
    private final MoistureCalculator calculator;
    private final DisplayDataOnOLED display;

    public MoistureCheckTask(Pin sensor, Pin pump, PumpControl pumpControl, DataCollection dataLog, MoistureCalculator calculator, DisplayDataOnOLED display) {
        this.sensor = sensor;
        this.pump = pump;
        this.pumpControl = pumpControl;
        this.dataLog = dataLog;
        this.calculator = calculator;
        this.display = display;
    }

    @Override
    public void run() {
        try {
            double analogValue = sensor.getValue();
            dataLog.addMoistureData(analogValue);

            double percent = calculator.convertToPercentage(analogValue);
            boolean pumpOn;

            if (percent < 30) {
                pump.setValue(1);
                Thread.sleep(5000);
                pump.setValue(0);
                pumpOn = true;
            } else if (percent < 60) {
                pump.setValue(1);
                Thread.sleep(2000);
                pump.setValue(0);
                pumpOn = true;
            } else {
                pump.setValue(0);
                pumpOn = false;
            }

            display.displayMoisture(percent, pumpOn, pumpControl.isManualMode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
