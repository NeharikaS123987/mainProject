package org.example;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;

public class PlantWateringSystem {
    private static final String PORT = "/dev/tty.usbserial-0001";
    private static final byte SENSOR_PIN = 15; // A1
    private static final byte PUMP_PIN = 2;    // D2

    private final MoistureCalculator calculator = new MoistureCalculator();
    private final DataCollection dataLog = new DataCollection();
    private final PumpControl pumpControl = new PumpControl();
    private final Timer timer = new Timer();

    public void start() throws IOException {
        IODevice board = new FirmataDevice(PORT);
        board.start();
        board.ensureInitializationIsDone();

        Pin sensorPin = board.getPin(SENSOR_PIN);
        Pin pumpPin = board.getPin(PUMP_PIN);
        sensorPin.setMode(Pin.Mode.ANALOG);
        pumpPin.setMode(Pin.Mode.OUTPUT);

        SSD1306 oled = new SSD1306(board, SSD1306.Address.ADDRESS_3C);
        oled.init();

        DisplayDataOnOLED display = new DisplayDataOnOLED(oled);
        PlotGraph graph = new PlotGraph(dataLog, calculator);

        timer.scheduleAtFixedRate(new MoistureCheckTask(sensorPin, pumpPin, pumpControl, dataLog, calculator, display), 0, 10000);
    }
}
