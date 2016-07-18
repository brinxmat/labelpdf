package no.deichman.labelpdf;

import com.google.gson.annotations.SerializedName;
import com.itextpdf.barcodes.BarcodeInter25;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsibility: allow creation of basic label.
 */
class Label {

    private static final double ROTATION_ANGLE_FLIP = 3.14159;
    private static final int WIDTH = 102;
    private static final int HEIGHT = 252;
    private static final double ROTATION_ANGLE = 1.5708;
    private static final int TEN = 10;
    private static final int FIELD_WIDTH = 230;
    private static final int EIGHTEEN = 18;
    private static final int FORTY = 40;
    private static final int TWELVE = 12;
    private static final int BARCODE_VERTICAL_OFFSET = -95;
    private static final int TWENTY = 20;
    private static final int SIX = 6;

    @SerializedName("callNumber")
    private String callNumber;

    @SerializedName("creator")
    private String creator;

    @SerializedName("title")
    private String title;

    @SerializedName("publicationDate")
    private String publicationDate;

    @SerializedName("holdingBranch")
    private String holdingBranch;

    @SerializedName("biblio")
    private String biblio;

    @SerializedName("copyNumber")
    private String copyNumber;

    @SerializedName("barcode")
    private String barcode;

    Label() {

    }

    String getCallNumber() {
        return callNumber;
    }

    void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    String getCreator() {
        return creator;
    }

    void setCreator(String creator) {
        this.creator = creator;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getPublicationDate() {
        return publicationDate;
    }

    void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    String getHoldingBranch() {
        return holdingBranch;
    }

    void setHoldingBranch(String holdingBranch) {
        this.holdingBranch = holdingBranch;
    }

    String getBiblio() {
        return biblio;
    }

    void setBiblio(String biblio) {
        this.biblio = biblio;
    }

    String getCopyNumber() {
        return copyNumber;
    }

    void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    String getBarcode() {
        return barcode;
    }

    void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    Label(String callNumber,
          String creator,
          String title,
          String publicationDate,
          String holdingBranch,
          String biblio,
          String copyNumber,
          String barcode) {

        setCallNumber(callNumber);
        setCreator(creator);
        setTitle(title);
        setPublicationDate(publicationDate);
        setHoldingBranch(holdingBranch);
        setBiblio(biblio);
        setCopyNumber(copyNumber);
        setBarcode(barcode);
    }

    private static final int ZERO = 0;
    private static final String REGULAR = "/resources/font/FreeSerif.otf";

    private transient PdfFont font = null;
    private transient PdfDocument pdfDocument = null;
    private transient PdfCanvas pdfCanvas = null;
    private transient Document document = null;

    private void setup(String filename) throws IOException {
        File file = new File(filename);
        file.getParentFile().mkdirs();


        PdfWriter pdfWriter = new PdfWriter(filename);
        pdfDocument = new PdfDocument(pdfWriter);

        Rectangle addressLabel = new Rectangle(ZERO,ZERO, WIDTH, HEIGHT);

        PageSize dymoAddressLabel = new PageSize(addressLabel);

        font = PdfFontFactory.createFont(new FontProvider().read(REGULAR), PdfEncodings.IDENTITY_H, true);

        document = new Document(pdfDocument, dymoAddressLabel);
        document.setFixedPosition(ZERO, ZERO, HEIGHT, WIDTH);
        document.setWidth(WIDTH);
        document.setHeight(HEIGHT);
        document.setFontSize(TEN);
        document.setFont(font);
        document.setMargins(ZERO, ZERO, ZERO, ZERO);

        PdfPage pdfPage = pdfDocument.addNewPage(dymoAddressLabel);
        pdfCanvas = new PdfCanvas(pdfPage);

    }

    void renderPDF(String filename) throws IOException {
        renderPDF(filename,
                getCallNumber(),
                getCreator(),
                getTitle(),
                getPublicationDate(),
                getHoldingBranch(),
                getBiblio(),
                getCopyNumber(),
                getBarcode());
    }

    void renderPDF(String filename,
                          String callNumber,
                          String creator,
                          String title,
                          String publicationDate,
                          String holdingBranch,
                          String biblio,
                          String copyNumber,
                          String barcode) throws IOException {

        setup(filename);

        Text callNumberText = new Text(fixFieldLength(callNumber, FieldType.CALLNUMER));

        java.util.List<Text> texts = new ArrayList<>();

        texts.add(callNumberText);
        texts.add(new Text(fixFieldLength(creator, FieldType.CREATOR)));
        texts.add(new Text(fixFieldLength(title, FieldType.TITLE)));

        final int[] horizontalPosition = {EIGHTEEN};
        texts.forEach(text -> {
            document.add(new Paragraph(text).setFixedPosition(
                    horizontalPosition[0],
                    TEN,
                    FIELD_WIDTH)
                    .setRotationAngle(ROTATION_ANGLE));

            horizontalPosition[0] = horizontalPosition[0] + TWELVE;
        });

        texts.clear();
        texts.add(new Text(publicationDate));
        texts.add(new Text(holdingBranch));
        texts.add(new Text(biblio));
        texts.add(new Text(copyNumber));

        final int[] verticalPosition = {TEN};

        texts.forEach(text -> {
            document.add(new Paragraph(text).setFixedPosition(horizontalPosition[0], verticalPosition[0], FIELD_WIDTH).setRotationAngle(ROTATION_ANGLE));
            if (verticalPosition[0] == TEN) {
                verticalPosition[0] = FORTY;
            } else {
                verticalPosition[0] = (verticalPosition[0] - 1) + (verticalPosition[0] - 2);
            }
        });

        setVerticalCallNumber(callNumberText);

        getBarcode(barcode, pdfDocument, font);

        document.close();
    }

    private void setVerticalCallNumber(Text callNumberText) {
        int callNumberVerticalOffsetReduction = TWENTY;

        if (callNumberText.getText().length() < FieldType.CALLNUMER.maxlength()) {
            callNumberVerticalOffsetReduction = TWENTY;
        } else if (callNumberText.getText().length() > FieldType.CALLNUMER.maxlength()) {
            int numberOfNewlines = callNumberText.getText().length() - callNumberText.getText().replace("\n", "").length();
            callNumberVerticalOffsetReduction = TWENTY - (numberOfNewlines * SIX);
        }

        document.add(new Paragraph(callNumberText)
                .setFixedPosition(
                        document.getWidth().getValue() - TWELVE,
                        document.getHeight() - (callNumberVerticalOffsetReduction),
                        FIELD_WIDTH)
                .setRotationAngle(ROTATION_ANGLE_FLIP));
    }

    private void getBarcode(String barcode, PdfDocument pdfDocument, PdfFont font) {
        BarcodeInter25 barcodeInter25 = new BarcodeInter25(pdfDocument);
        barcodeInter25.setCodeType(BarcodeInter25.ALIGN_LEFT);
        barcodeInter25.setCode(barcode);
        barcodeInter25.setFont(font);
        AffineTransform transformationMatrix = AffineTransform.getRotateInstance(ROTATION_ANGLE);
        pdfCanvas.concatMatrix(transformationMatrix);
        transformationMatrix = AffineTransform.getTranslateInstance(TEN, BARCODE_VERTICAL_OFFSET);
        pdfCanvas.concatMatrix(transformationMatrix);
        barcodeInter25.placeBarcode(pdfCanvas, Color.BLACK, Color.BLACK);
    }

    private String fixFieldLength(String string, FieldType type) {
        String retVal = string;
        if (string.length() > type.maxlength() && type.equals(FieldType.CALLNUMER)) {
            retVal = string.replace(" ", " \n");
        } else if (string.length() > type.maxlength() && (type.equals(FieldType.TITLE) || type.equals(FieldType.CREATOR))) {
            retVal = string.substring(0, type.maxlength()-1) + "…";
        }
        return retVal;
    }
}
