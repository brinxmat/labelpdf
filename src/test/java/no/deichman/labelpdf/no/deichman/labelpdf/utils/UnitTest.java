package no.deichman.labelpdf.no.deichman.labelpdf.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Responsibility: test enum Unit.
 */
public class UnitTest {
    @Test
    public void getUnit() throws Exception {
        assertTrue(Unit.MM.getUnit().equals("millimetres"));
        assertTrue(Unit.PT.getUnit().equals("points"));
    }
}
