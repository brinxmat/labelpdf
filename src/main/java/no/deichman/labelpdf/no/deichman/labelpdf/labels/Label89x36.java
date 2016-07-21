package no.deichman.labelpdf.no.deichman.labelpdf.labels;

/**
 * Responsibility: provide standard template for labels 89mm x 36mm.
 */
public class Label89x36 extends LabelTemplateImpl {

    public static final double WIDTH_IN_MM = 36.0;
    public static final double HEIGHT_IN_MM = 89.0;

    public Label89x36() {
        super(WIDTH_IN_MM, HEIGHT_IN_MM);
    }

}
