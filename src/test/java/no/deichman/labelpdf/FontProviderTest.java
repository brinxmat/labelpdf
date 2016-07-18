package no.deichman.labelpdf;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Responsibility: test resource reader class.
 */
public class FontProviderTest {
    @Test
    public void test_read() throws IOException {
        FontProvider fontProvider = new FontProvider();
        String result = fontProvider.read("/resources/font/FreeSerif.otf");
        assertEquals(Label.class.getResource("/resources/font/FreeSerif.otf").getPath(), result);
    }

    @Test
    public void test_ide_read() throws IOException {
        FontProvider fontProvider = new FontProvider();
        String result = fontProvider.getFontResource("/resources/font/FreeSerif.otf");
        assertEquals(Label.class.getResource("/resources/font/FreeSerif.otf").getPath(), result);
    }

    @Test
    public void test_jar_read() throws IOException {
        FontProvider fontProvider = new FontProvider();
        String result = fontProvider.createLocalFile("/resources/font/FreeSerif.otf");
        assertEquals("FreeSerif.otf", result);
    }

}
