package no.deichman.labelpdf.fontutils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import no.deichman.labelpdf.Label;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Responsibility: manage reading resources.
 */
public final class FontProvider {

    private static final String SERIF = "/resources/font/FreeSerif.otf";
    private static final String SANS = "/resources/font/FreeSans.otf";

    private FontProvider() {

    }

    public static PdfFont getFont(Font font) throws IOException {

        return PdfFontFactory.createFont(
                IOUtils.toByteArray(
                        Label.class.getResourceAsStream(font.getPath())
                ), PdfEncodings.IDENTITY_H, true);

    }

}
