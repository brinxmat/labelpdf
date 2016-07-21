package no.deichman.labelpdf.no.deichman.labelpdf.labels;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Responsibility: test LabelTemplateImpl.
 */
public class LabelTemplateImplTest {

    private static final double TWENTY_TWO_POINT_SEVEN = 22.7;
    private static final int DELTA = 0;
    private static final int FIFTY_FOUR = 54;
    private static final double NINETEEN_POINT_ZERO_FIVE = 19.05;
    private static final int ONE_HUNDRED_AND_FIFTY_THREE = 153;
    private static final double FIFTY_THREE_POINT_NINE_EIGHT = 53.98;
    private static final int FORTY_FIVE = 45;

    @Test
    public void getWidthInMM() throws Exception {
        LabelTemplate labelTemplate = new LabelTemplateImpl();
        labelTemplate.setWidthInMM(TWENTY_TWO_POINT_SEVEN);
        assertEquals(TWENTY_TWO_POINT_SEVEN, labelTemplate.getWidthInMM(), DELTA);
        LabelTemplate labelTemplate2 = new LabelTemplateImpl();
        labelTemplate2.setWidthInPoints(FIFTY_FOUR);
        assertEquals(NINETEEN_POINT_ZERO_FIVE, labelTemplate2.getWidthInMM(), DELTA);
    }

    @Test
    public void getHeightInMM() throws Exception {
        LabelTemplate labelTemplate = new LabelTemplateImpl();
        labelTemplate.setHeightInMM(TWENTY_TWO_POINT_SEVEN);
        assertEquals(TWENTY_TWO_POINT_SEVEN, labelTemplate.getHeightInMM(), DELTA);
        LabelTemplate labelTemplate2 = new LabelTemplateImpl();
        labelTemplate2.setHeightInPoints(ONE_HUNDRED_AND_FIFTY_THREE);
        assertEquals(FIFTY_THREE_POINT_NINE_EIGHT, labelTemplate2.getHeightInMM(), DELTA);
    }

    @Test
    public void getWidthInPoints() throws Exception {
        LabelTemplate labelTemplate = new LabelTemplateImpl();
        labelTemplate.setWidthInPoints(FORTY_FIVE);
        assertEquals(FORTY_FIVE, labelTemplate.getWidthInPoints(), DELTA);
        LabelTemplate labelTemplate2 = new LabelTemplateImpl();
        labelTemplate2.setWidthInMM(FIFTY_FOUR);
        assertEquals(ONE_HUNDRED_AND_FIFTY_THREE, labelTemplate2.getWidthInPoints(), DELTA);
    }

    @Test
    public void getHeightInPoints() throws Exception {
        LabelTemplate labelTemplate = new LabelTemplateImpl();
        labelTemplate.setHeightInPoints(FORTY_FIVE);
        assertEquals(FORTY_FIVE, labelTemplate.getHeightInPoints(), DELTA);
        LabelTemplate labelTemplate2 = new LabelTemplateImpl();
        labelTemplate2.setHeightInMM(FIFTY_FOUR);
        assertEquals(ONE_HUNDRED_AND_FIFTY_THREE, labelTemplate2.getHeightInPoints(), DELTA);
    }
}
