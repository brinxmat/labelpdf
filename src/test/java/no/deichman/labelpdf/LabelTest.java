package no.deichman.labelpdf;

import com.google.gson.Gson;
import no.deichman.labelpdf.data.LabelData;
import no.deichman.labelpdf.data.LabelDataImpl;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

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
                + "\t\"callNumber\": \"820.000000000000 Brims\",\n"
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


    @Test
    public void can_provide_byte_array_stream() throws Exception {
        String title = "Swa swa swilice æåø";
        String callnumber = "123 Moa";
        String barcode = "03011231231444";
        String creator = "Mao, Mu";
        String publicationDate = "1998";
        String holdingBranch = "HUTL";
        String biblio = "123432";
        String copyNumber = "004";
        String jsondata = getJSON(title,
                callnumber,
                barcode,
                creator,
                publicationDate,
                holdingBranch,
                biblio,
                copyNumber);

        LabelData labelData = new Gson().fromJson(jsondata, LabelDataImpl.class);
        Label label = new Label();
        label.setData(labelData);
        label.renderPDFAsBAOS();

        assertTrue(parsePdf(label, callnumber));
        assertTrue(parsePdf(label, creator));
        assertTrue(parsePdf(label, barcode));
        assertTrue(parsePdf(label, title));
        assertTrue(parsePdf(label, publicationDate));
        assertTrue(parsePdf(label, holdingBranch));
        assertTrue(parsePdf(label, biblio));
        assertTrue(parsePdf(label, copyNumber));
        assertTrue(parsePdf(label, barcode));

    }

    private String getJSON(String title, String callnumber, String barcode, String creator, String publicationDate, String holdingBranch, String biblio, String copyNumber) {
        return "{\n"
                + "\t\"callNumber\": \"" + callnumber + "\",\n"
                + "\t\"creator\": \"" + creator + "\",\n"
                + "\t\"title\": \"" + title + "\",\n"
                + "\t\"publicationDate\": \"" + publicationDate + "\",\n"
                + "\t\"holdingBranch\": \"" + holdingBranch + "\",\n"
                + "\t\"biblio\": \"" + biblio + "\",\n"
                + "\t\"copyNumber\": \"" + copyNumber + "\",\n"
                + "\t\"barcode\": \"" + barcode + "\"\n"
                + "}";
    }

    private boolean parsePdf(Label label, String testText) throws IOException {
        byte[] bytes = label.getBytes();

        String pdfDocumentText = null;
        COSDocument cosDocument = null;
        PDDocument pdDocument = null;

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            PDFParser pdfParser = new PDFParser(new RandomAccessBufferedFileInputStream(bis));
            pdfParser.parse();
            cosDocument = pdfParser.getDocument();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdDocument = new PDDocument(cosDocument);
            pdfDocumentText = pdfTextStripper.getText(pdDocument);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDocument != null) {
                    cosDocument.close();
                }
                if (pdDocument != null) {
                    pdDocument.close();
                }
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }

        boolean retVal = false;
        if (pdfDocumentText != null && pdfDocumentText.replace("\n", "").contains(testText)) {
            retVal = true;
        }

        return retVal;

    }
}
