package no.deichman.labelpdf;

import com.itextpdf.kernel.font.PdfFont;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Responsibility: test resource reader class.
 */
public class FontProviderTest {
    @Test
    public void test_it_provides_pdffont() throws IOException {
        PdfFont pdfFont = FontProvider.getFont();
        assertNotNull(pdfFont);
        assertTrue(pdfFont.isEmbedded());
    }
}
