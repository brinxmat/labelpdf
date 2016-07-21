package no.deichman.labelpdf.no.deichman.labelpdf.data;

import com.google.gson.Gson;
import no.deichman.labelpdf.data.LabelData;
import no.deichman.labelpdf.data.LabelDataImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Responsibility: test label data class.
 */
public class LabelDataImplTest {
    @Test
    public void test_constructor() {
        assertNotNull(new LabelDataImpl("820 Tes", "Testson, Test", "Test's book", "2000", "Brc1", "2000110", "001", "03010000001001"));
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

        LabelData labelData = new LabelDataImpl();
        labelData.setCallNumber(callNumber);
        labelData.setCreator(creator);
        labelData.setTitle(title);
        labelData.setPublicationDate(publicationDate);
        labelData.setHoldingBranch(holdingBranch);
        labelData.setBiblio(biblio);
        labelData.setCopyNumber(copyNumber);
        labelData.setBarcode(barcode);

        assertEquals(callNumber, labelData.getCallNumber());
        assertEquals(creator, labelData.getCreator());
        assertEquals(title, labelData.getTitle());
        assertEquals(publicationDate, labelData.getPublicationDate());
        assertEquals(holdingBranch, labelData.getHoldingBranch());
        assertEquals(biblio, labelData.getBiblio());
        assertEquals(copyNumber, labelData.getCopyNumber());
        assertEquals(barcode, labelData.getBarcode());

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

        LabelData labelData = new Gson().fromJson(jsondata, LabelDataImpl.class);

        assertEquals("820.000 Brims", labelData.getCallNumber());
    }

}