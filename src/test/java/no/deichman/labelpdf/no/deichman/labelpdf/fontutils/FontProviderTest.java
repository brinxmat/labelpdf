package no.deichman.labelpdf.no.deichman.labelpdf.fontutils;

import com.itextpdf.kernel.font.PdfFont;
import no.deichman.labelpdf.fontutils.Font;
import no.deichman.labelpdf.fontutils.FontProvider;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Responsibility: test resource reader class.
 */
public class FontProviderTest {
    @Test
    public void test_it_provides_pdffonts() throws IOException {

        PdfFont pdfFont = null;

        for (Font font: Font.values()) {
            pdfFont = FontProvider.getFont(font);
            assertNotNull(pdfFont);
            assertTrue(pdfFont.isEmbedded());
        }
    }
}
