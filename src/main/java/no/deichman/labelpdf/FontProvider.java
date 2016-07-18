package no.deichman.labelpdf;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * Responsibility: manage reading resources.
 */
class FontProvider {


    final String read(String resource) throws IOException {
        final File jarFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        String retVal = null;

        if (jarFile.isFile()) {
            retVal = createLocalFile(resource);
        } else { // Run with IDE
            retVal = getFontResource(resource);
        }

        return retVal;
    }

    final String getFontResource(String resource) {
        String retVal = null;
        final URL url = this.getClass().getResource(resource);
        if (url != null) {
           retVal = url.getPath();
        }
        return retVal;
    }

    final String createLocalFile(String resource) throws IOException {
        resource.substring(resource.lastIndexOf("/") -1, resource.length());
        File file = new File("FreeSerif.otf");
        if (file.exists() && !file.isDirectory()) {
            OutputStream out = new FileOutputStream(file);
            out.write(IOUtils.toByteArray(Label.class.getResourceAsStream("/resources/font/FreeSerif.otf")));
        }
        return file.getPath();
    }

}
