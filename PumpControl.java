package org.example;

public class PumpControl {
    private boolean manualMode = false;

    public void toggleManualMode() {
        manualMode = !manualMode;
    }

    public boolean isManualMode() {
        return manualMode;
    }
}
