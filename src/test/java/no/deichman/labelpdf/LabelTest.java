package no.deichman.labelpdf;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Responsibility: test Label class.
 */
public class LabelTest {

    @Test
    public void test_constructor() {
        assertNotNull(new Label("820 Tes", "Testson, Test", "Test's book", "2000", "Brc1", "2000110", "001", "03010000001001"));
    }

    @Test
    public void test_getting_setting() {
        String callNumber = "810 Ges";
        String creator = "Gestson, Gest";
        String title = "A title";
        String publicationDate = "2000";
        String holdingBranch = "HUTL";
        String biblio = "1234567";
        String copyNumber = "002";
        String barcode = "03011231233001";

        Label label = new Label();
        label.setCallNumber(callNumber);
        label.setCreator(creator);
        label.setTitle(title);
        label.setPublicationDate(publicationDate);
        label.setHoldingBranch(holdingBranch);
        label.setBiblio(biblio);
        label.setCopyNumber(copyNumber);
        label.setBarcode(barcode);

        assertEquals(callNumber, label.getCallNumber());
        assertEquals(creator, label.getCreator());
        assertEquals(title, label.getTitle());
        assertEquals(publicationDate, label.getPublicationDate());
        assertEquals(holdingBranch, label.getHoldingBranch());
        assertEquals(biblio, label.getBiblio());
        assertEquals(copyNumber, label.getCopyNumber());
        assertEquals(barcode, label.getBarcode());

    }

    @Test
    public void can_render_pdf() throws IOException {
        Label label = new Label();
        label.renderPDF("resource/test/been.pdf",
                "820.000 Brims",
                "Brims, Timo",
                "Å hello ЙЖ asdasdsadsadasdasdasdasdasdفقك",
                "2014",
                "HUTL",
                "3000321",
                "001",
                "03011231231231"
        );
    }

}
