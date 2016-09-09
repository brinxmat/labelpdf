package no.deichman.labelpdf.no.deichman.labelpdf.fontutils;

import no.deichman.labelpdf.fontutils.Font;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Responsibility: test Font enum.
 */
public class FontTest {
    @Test
    public void test_presence_of_font_list() {
        String[] fonts = new String[] {"/resources/font/FreeSerif.otf", "/resources/font/FreeSans.otf"};
        for (Font font : Font.values()) {
            assertTrue(Arrays.asList(fonts).contains(font.getPath()));
        }
    }

}
