package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest {
    @Test
    public void testPercentConversion() {
        MoistureCalculator calc = new MoistureCalculator();
        assertEquals(0.0, calc.convertToPercentage(740), 0.01);
        assertEquals(100.0, calc.convertToPercentage(495), 0.01);
        assertTrue(calc.convertToPercentage(600) > 50);
    }

    @Test
    public void testLogging() {
        DataCollection d = new DataCollection();
        d.addMoistureData(600);
        d.addMoistureData(610);
        assertEquals(2, d.getMoistureData().size());
        assertEquals(610, d.getLatestValue(), 0.01);
    }

    @Test
    public void testModeToggle() {
        PumpControl p = new PumpControl();
        assertFalse(p.isManualMode());
        p.toggleManualMode();
        assertTrue(p.isManualMode());
    }
}
