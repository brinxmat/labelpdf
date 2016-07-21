package no.deichman.labelpdf;

import com.google.gson.Gson;
import no.deichman.labelpdf.data.LabelData;
import no.deichman.labelpdf.data.LabelDataImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Responsibility: test Label class.
 */
public class LabelTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


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
        LabelData labelData = new Gson().fromJson(jsondata, LabelDataImpl.class);

        Label label = new Label();
        label.setData(labelData);
        label.renderPDF(temporaryFolderPath + "oo.pdf");

        assertTrue("Could not find file", new File(temporaryFolderPath + "oo.pdf").exists());

    }
}
