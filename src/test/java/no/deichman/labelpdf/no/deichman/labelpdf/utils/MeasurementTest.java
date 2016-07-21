package no.deichman.labelpdf.no.deichman.labelpdf.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Responsibility: test units class.
 */
public class MeasurementTest {

    private static final double TWENTY_TWO_POINT_FIVE = 22.5;
    private static final int DELTA = 0;
    private static final int SIXTY_THREE = 63;
    private static final int TWENTY_TWO = 22;
    private static final double SEVEN_POINT_SEVEN_SIX = 7.76;

    @Test
    public void setSizeInMillimetres() throws Exception {
        Measurement measurement = new Measurement();
        measurement.setSizeInMillimetres(TWENTY_TWO_POINT_FIVE);

        assertEquals(TWENTY_TWO_POINT_FIVE, measurement.getSizeInMillimetres(), DELTA);
        assertEquals(SIXTY_THREE, measurement.getSizeInPoints(), DELTA);
    }

    @Test
    public void setSizeInPoints() throws Exception {
        Measurement measurement = new Measurement();
        measurement.setSizeInPoints(TWENTY_TWO);
        assertEquals(TWENTY_TWO, measurement.getSizeInPoints(), DELTA);
        assertEquals(SEVEN_POINT_SEVEN_SIX, measurement.getSizeInMillimetres(), DELTA);
    }
}
