package org.example;

import org.firmata4j.ssd1306.SSD1306;

public class DisplayDataOnOLED {
    private final SSD1306 oled;

    public DisplayDataOnOLED(SSD1306 oled) {
        this.oled = oled;
    }

    public void displayMoisture(double percent, boolean pumpOn, boolean manual) {
        oled.clear();
        oled.setCursor(0, 0);
        oled.print("Moisture: " + String.format("%.1f", percent) + "%");
        oled.setCursor(0, 2);
        oled.print("Pump: " + (pumpOn ? "ON" : "OFF"));
        oled.setCursor(0, 4);
        oled.print("Mode: " + (manual ? "MANUAL" : "AUTO"));
        oled.display();
    }
}
