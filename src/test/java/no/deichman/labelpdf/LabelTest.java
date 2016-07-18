package no.deichman.labelpdf;

import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Responsibility: test Label class.
 */
public class LabelTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

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
    public void can_render_pdf() throws Exception {
        Label label = new Label();
        String outputFile = "been.pdf";
        String temporaryFolderPath = temporaryFolder.getRoot().getAbsolutePath();

        label.renderPDF(temporaryFolderPath + outputFile,
                "820.000 Brims",
                "Brims, Timo",
                "Å hello ЙЖ asdasdsadsadasdasdasdasdasdفقك",
                "2014",
                "HUTL",
                "3000321",
                "001",
                "03011231231231"
        );

        assertTrue("File did not exist", new File(temporaryFolderPath + outputFile).exists());
    }

    @Test
    public void can_map_from_json() {
        String jsondata = "{\n"
                + "\t\"callNumber\": \"820.000 Brims\",\n"
                +"\t\"creator\": \"Brims, Timo\",\n"
                + "\t\"title\": \"Å hello ЙЖ asdasdsadsadasdasdasdasdasdفقك\",\n"
                + "\t\"publicationDate\": \"2014\",\n"
                + "\t\"holdingBranch\": \"HUTL\",\n"
                + "\t\"biblio\": \"3000321\",\n"
                + "\t\"copyNumber\": \"001\",\n"
                + "\t\"barcode\": \"03011231231231\"\n"
                + "}";

        Label label = new Gson().fromJson(jsondata, Label.class);

        assertEquals("820.000 Brims", label.getCallNumber());
    }

    @Test
    public void can_render_existing_object() throws Exception {
        String temporaryFolderPath = temporaryFolder.getRoot().getAbsolutePath();

        String jsondata = "{\n"
                + "\t\"callNumber\": \"820.000 Brims\",\n"
                +"\t\"creator\": \"Brims, Timo\",\n"
                + "\t\"title\": \"Å hello ЙЖ asdasdsadsadasdasdasdasdasdفقك\",\n"
                + "\t\"publicationDate\": \"2014\",\n"
                + "\t\"holdingBranch\": \"HUTL\",\n"
                + "\t\"biblio\": \"3000321\",\n"
                + "\t\"copyNumber\": \"001\",\n"
                + "\t\"barcode\": \"03011231231231\"\n"
                + "}";
        Label label = new Gson().fromJson(jsondata, Label.class);

        label.renderPDF(temporaryFolderPath + "oo.pdf");

        assertTrue("Could not find file", new File(temporaryFolderPath + "oo.pdf").exists());

    }
}
