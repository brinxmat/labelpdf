package no.deichman.labelpdf.no.deichman.labelpdf.labels;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Responsibility: test Label89x36 class.
 */
public class Label89x36Test {

    private static final int THIRTY_SIX = 36;
    private static final int ZERO = 0;
    private static final int EIGHTY_NINE = 89;
    private static final double ONE_HUNDRED_AND_TWO = 102.0;
    private static final double TWO_HUNDRED_AND_FIFTY_TWO = 252.0;

    @Test
    public void test_label_template() throws Exception {
        LabelTemplate label = new Label89x36();
        assertEquals(THIRTY_SIX, label.getWidthInMM(), ZERO);
        assertEquals(EIGHTY_NINE, label.getHeightInMM(), ZERO);
        assertEquals(ONE_HUNDRED_AND_TWO, label.getPageSize().getWidth(), ZERO);
        assertEquals(TWO_HUNDRED_AND_FIFTY_TWO, label.getPageSize().getHeight(), ZERO);
    }
}
