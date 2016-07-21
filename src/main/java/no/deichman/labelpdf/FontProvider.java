package no.deichman.labelpdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Responsibility: manage reading resources.
 */
final class FontProvider {

    private static final String REGULAR = "/resources/font/FreeSerif.otf";

    private FontProvider() {

    }

    static PdfFont getFont() throws IOException {

        return PdfFontFactory.createFont(
                IOUtils.toByteArray(
                        Label.class.getResourceAsStream(REGULAR)
                ), PdfEncodings.IDENTITY_H, true);

    }

}
