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
import no.deichman.labelpdf.no.deichman.labelpdf.labels.Label89x36;
import no.deichman.labelpdf.no.deichman.labelpdf.labels.LabelTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Responsibility: allow creation of basic label.
 */
class Label {

    private static final double ROTATION_ANGLE_FLIP = 3.14159;
    private static final double ROTATION_ANGLE = 1.5708;
    private static final int TEN = 10;
    private static final int FIELD_WIDTH = 230;
    private static final int EIGHTEEN = 18;
    private static final int FORTY = 40;
    private static final int TWELVE = 12;
    private static final int BARCODE_VERTICAL_OFFSET = -95;
    private static final int TWENTY = 20;
    private static final int SIX = 6;
    private static final int ZERO = 0;

    private PdfFont font = null;
    private PdfDocument pdfDocument = null;
    private PdfCanvas pdfCanvas = null;
    private Document document = null;
    private LabelData labelData = null;

    private void setup(String filename) throws Exception {
        File file = new File(filename);
        file.getParentFile().mkdirs();

        PdfWriter pdfWriter = new PdfWriter(filename);
        pdfDocument = new PdfDocument(pdfWriter);

        LabelTemplate label = new Label89x36();
        PageSize pageSize = label.getPageSize();
        int height = label.getHeightInPoints();
        int width = label.getWidthInPoints();

        font = FontProvider.getFont();

        document = new Document(pdfDocument, pageSize);
        document.setFixedPosition(ZERO, ZERO, height, width);
        document.setWidth(width);
        document.setHeight(height);
        document.setFontSize(TEN);
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

    void renderPDF(String filename,
                          String callNumber,
                          String creator,
                          String title,
                          String publicationDate,
                          String holdingBranch,
                          String biblio,
                          String copyNumber,
                          String barcode) throws Exception {

        setup(filename);

        Text callNumberText = new Text(fixFieldLength(Optional.ofNullable(callNumber).orElse(""), FieldType.CALLNUMER));

        java.util.List<Text> texts = new ArrayList<>();

        texts.add(callNumberText);
        texts.add(new Text(fixFieldLength(Optional.ofNullable(creator).orElse(""), FieldType.CREATOR)));
        texts.add(new Text(fixFieldLength(Optional.ofNullable(title).orElse(""), FieldType.TITLE)));

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
        texts.add(new Text(Optional.ofNullable(publicationDate).orElse("")));
        texts.add(new Text(Optional.ofNullable(holdingBranch).orElse("")));
        texts.add(new Text(Optional.ofNullable(biblio).orElse("")));
        texts.add(new Text(Optional.ofNullable(copyNumber).orElse("")));

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

        getBarcode(Optional.ofNullable(barcode)
                .orElseThrow(() -> new Exception("You need to add a barcode")), pdfDocument, font);

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

    public void setData(LabelData labelData) {
        this.labelData = labelData;
    }
}
