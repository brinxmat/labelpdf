package no.deichman.labelpdf;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Responsibility: Test App.
 */
public class AppTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String EOL = System.getProperty("line.separator");
    private ByteArrayOutputStream consoleText;
    private PrintStream console;
    private String jsondata;
    private String filename;

    @Before
    public void setup() throws NoSuchMethodException, ClassNotFoundException {
        consoleText = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(consoleText));

        jsondata = "{"
                + "\"callNumber\": \"820.000 Brims\",\n"
                + "        \"creator\": \"Brims, Timo\",\n"
                + "                \"title\": \"Å hello ЙЖ asdasdsadsadasdasdasdasdasdفقك\",\n"
                + "                \"publicationDate\": \"2014\",\n"
                + "                \"holdingBranch\": \"HUTL\",\n"
                + "                \"biblio\": \"3000321\",\n"
                + "                \"copyNumber\": \"001\",\n"
                + "                \"barcode\": \"03011231231231\""
                + "}";

        filename = "testApp.pdf";
    }

    @Test
    public void test_main_method_with_no_args_fails() throws IOException {
        App.main(null);
        assertEquals("Parsing failed: Missing required options: data, output" + EOL, consoleText.toString());
    }

    @Test
    public void test_main_method() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        String[] args = new String[2];
        String temporaryFolderPath = temporaryFolder.getRoot().getAbsolutePath();

        args[0] = "--data=" + jsondata;
        args[1] = "--output=" +temporaryFolderPath + filename;
        appMain(args);

        assertTrue(new File(temporaryFolderPath + filename).exists());
        assertEquals("", consoleText.toString());
    }

    @After
    public void teardown() {
        System.setOut(console);
    }

    private void appMain(String[] args) throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException {
        Class<?> clazz = Class.forName("no.deichman.labelpdf.App");
        Method appMain;
        appMain = clazz.getMethod("main", String[].class);
        appMain.invoke(null, new Object[] {args});
    }
}
