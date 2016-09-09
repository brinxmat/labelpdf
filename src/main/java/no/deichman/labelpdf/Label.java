package no.deichman.labelpdf;

import com.itextpdf.barcodes.BarcodeInter25;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import no.deichman.labelpdf.data.LabelData;
import no.deichman.labelpdf.fontutils.Font;
import no.deichman.labelpdf.fontutils.FontProvider;
import no.deichman.labelpdf.no.deichman.labelpdf.labels.Label89x36;
import no.deichman.labelpdf.no.deichman.labelpdf.labels.LabelTemplate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Responsibility: allow creation of basic label.
 */
public final class Label {

    private static final double ROTATION_ANGLE_FLIP = 3.14159;
    private static final double ROTATION_ANGLE = 1.5708;
    private static final int TEN = 10;
    private static final int FIELD_WIDTH = 230;
    private static final int EIGHTEEN = 18;
    private static final int FORTY = 40;
    private static final int TWELVE = 12;
    private static final int FOURTEEN = 14;
    private static final int BARCODE_VERTICAL_OFFSET = -95;
    private static final int TWENTY = 20;
    private static final int ZERO = 0;
    private static final int BARCODE_WIDTH = 165;
    private static final int THIRTEEN = 13;
    private static final String KOMMUNE_NUMBER = "0301";
    private static final float[] VERTICAL_POSITION = new float[]{10, 43, 80, 110, 159};
    public static final int SIX = 6;
    public static final int THREE = 3;

    private PdfFont font = null;
    private PdfDocument pdfDocument = null;
    private PdfCanvas pdfCanvas = null;
    private Document document = null;
    private LabelData labelData = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;

    public byte[] getBytes() {
        return byteArrayOutputStream.toByteArray();
    }

    private PdfWriter pdfWriter = null;

    private void setup(String filename) throws Exception {

        if (filename == null) {
            setupWithBAOS();
        } else {
            File file = new File(filename);
            file.getParentFile().mkdirs();

            pdfWriter = new PdfWriter(filename);
        }
        setup();
    }

    private void setupWithBAOS() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        pdfWriter = new PdfWriter(byteArrayOutputStream);
    }

    private void setup() throws Exception {
        pdfDocument = new PdfDocument(pdfWriter);

        LabelTemplate label = new Label89x36();
        PageSize pageSize = label.getPageSize();
        int height = label.getHeightInPoints();
        int width = label.getWidthInPoints();

        font = FontProvider.getFont(Font.SERIF);

        document = new Document(pdfDocument, pageSize);
        document.setFixedPosition(ZERO, ZERO, height, width);
        document.setWidth(width);
        document.setHeight(height);
        document.setFontSize(TWELVE);
        document.setFont(font);
        document.setMargins(ZERO, ZERO, ZERO, ZERO);

        PdfPage pdfPage = pdfDocument.addNewPage(pageSize);
        pdfCanvas = new PdfCanvas(pdfPage);

    }

    void renderPDF(String filename) throws Exception {
        renderPDF(filename,
                labelData.getCallNumber(),
                labelData.getCreator(),
                labelData.getTitle(),
                labelData.getPublicationDate(),
                labelData.getHoldingBranch(),
                labelData.getBiblio(),
                labelData.getCopyNumber(),
                labelData.getBarcode());
    }

    public void renderPDFAsBAOS() throws Exception {
        renderPDF(null,
                labelData.getCallNumber(),
                labelData.getCreator(),
                labelData.getTitle(),
                labelData.getPublicationDate(),
                labelData.getHoldingBranch(),
                labelData.getBiblio(),
                labelData.getCopyNumber(),
                labelData.getBarcode());
    }

    public void renderPDF(String filename,
                          String callNumber,
                          String creator,
                          String title,
                          String publicationDate,
                          String holdingBranch,
                          String biblio,
                          String copyNumber,
                          String barcode) throws Exception {

        setup(filename);

        String callNumberString = Optional.ofNullable(callNumber).orElse("");

        java.util.List<Text> texts = new ArrayList<>();

        texts.add(new Text(callNumberString));
        texts.add(new Text(fixFieldLength(Optional.ofNullable(creator).orElse(""), FieldType.CREATOR)));
        texts.add(new Text(fixFieldLength(Optional.ofNullable(title).orElse(""), FieldType.TITLE)));

        final int[] horizontalPosition = {EIGHTEEN};
        texts.forEach(text -> {
            document.add(new Paragraph(text).setFixedPosition(
                    horizontalPosition[0],
                    TEN,
                    FIELD_WIDTH)
                    .setRotationAngle(ROTATION_ANGLE));

            horizontalPosition[0] = horizontalPosition[0] + FOURTEEN;
        });

        texts.clear();
        texts.add(new Text(Optional.ofNullable(publicationDate).orElse("")));
        texts.add(new Text(Optional.ofNullable(holdingBranch).orElse("")));
        texts.add(new Text((KOMMUNE_NUMBER)));
        texts.add(new Text(Optional.ofNullable(biblio).orElse("")));
        texts.add(new Text(Optional.ofNullable(copyNumber).orElse("")));

        for (int i = 0; i<texts.size(); i++) {
            document.add(
                    new Paragraph(texts.get(i)).
                    setFixedPosition(horizontalPosition[0], VERTICAL_POSITION[i], FIELD_WIDTH).
                            setRotationAngle(ROTATION_ANGLE)
            );
        }

        if (callNumberString.length() > 0) {
            setVerticalCallNumber(callNumberString);
        }
        getBarcode(Optional.ofNullable(barcode)
                .orElseThrow(() -> new Exception("You need to add a barcode")), pdfDocument, font);

        document.close();
    }

    private void setVerticalCallNumber(String spineLabelText) throws IOException {

        int fontSize = THIRTEEN;

        String formattedText = spineLabelText.replace(" ", "\n");
        Text callNumber = new Text(formattedText);
        if (spineLabelText.matches(".*[0-9]{8}.*")) {
            callNumber.setCharacterSpacing(-1);
        }
        PdfFont spineFont = FontProvider.getFont(Font.SANS);
        callNumber.setFontSize(fontSize);
        callNumber.setFont(spineFont);

        float lines = getTextHeight(formattedText);

        document.add(new Paragraph(callNumber)
                .setFixedPosition(
                        document.getWidth().getValue() - SIX,
                        document.getHeight() - (lines),
                        FIELD_WIDTH)
                .setRotationAngle(ROTATION_ANGLE_FLIP)
        );
    }

    private float getTextHeight(String formattedText) {
        return (formattedText.length() - (formattedText.replace("\n", "").length())) + THREE;
    }

    private void getBarcode(String barcode, PdfDocument pdfDocument, PdfFont font) {
        BarcodeInter25 barcodeInter25 = new BarcodeInter25(pdfDocument);
        barcodeInter25.setCodeType(BarcodeInter25.ALIGN_LEFT);
        barcodeInter25.setCode(barcode);
        barcodeInter25.setFont(font);
        barcodeInter25.fitWidth(BARCODE_WIDTH);
        AffineTransform transformationMatrix = AffineTransform.getRotateInstance(ROTATION_ANGLE);
        pdfCanvas.concatMatrix(transformationMatrix);
        transformationMatrix = AffineTransform.getTranslateInstance(TEN, BARCODE_VERTICAL_OFFSET);
        pdfCanvas.concatMatrix(transformationMatrix);
        barcodeInter25.placeBarcode(pdfCanvas, Color.BLACK, Color.BLACK);
    }

    private String fixFieldLength(String string, FieldType type) {
        String retVal = string;
        if (string.length() > type.maxlength() && (type.equals(FieldType.TITLE) || type.equals(FieldType.CREATOR))) {
            retVal = string.substring(0, type.maxlength() - 1) + "…";
        }
        return retVal;
    }

    public void setData(LabelData labelData) {
        this.labelData = labelData;
    }
}
