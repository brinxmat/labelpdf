package no.deichman.labelpdf.no.deichman.labelpdf.labels;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import no.deichman.labelpdf.no.deichman.labelpdf.utils.Measurement;

import java.util.Optional;

/**
 * Responsibility: provide access to default parameters of labels.
 */
class LabelTemplateImpl implements LabelTemplate {
    private static final float ZERO = 0;
    private Measurement width = new Measurement();
    private Measurement height = new Measurement();

    LabelTemplateImpl() {}

    LabelTemplateImpl(double widthInMM, double heightInMM) {
        setWidthInMM(widthInMM);
        setHeightInMM(heightInMM);
    }

    LabelTemplateImpl(int widthInPT, int heightInPT) {
        setWidthInPoints(widthInPT);
        setHeightInPoints(heightInPT);
    }

    @Override
    public final void setWidthInMM(double widthInMM) {
        width.setSizeInMillimetres(widthInMM);
    }

    @Override
    public final double getWidthInMM() {
        return width.getSizeInMillimetres();
    }

    @Override
    public final void setHeightInMM(double heightInMM) {
        height.setSizeInMillimetres(heightInMM);
    }

    @Override
    public final double getHeightInMM() {
        return height.getSizeInMillimetres();
    }

    @Override
    public final void setWidthInPoints(int widthInPoints) {
        width.setSizeInPoints(widthInPoints);
    }

    @Override
    public final int getWidthInPoints() {
        return width.getSizeInPoints();
    }

    @Override
    public final void setHeightInPoints(int heightInPoints) {
        height.setSizeInPoints(heightInPoints);
    }

    @Override
    public final int getHeightInPoints() {
        return height.getSizeInPoints();
    }

    @Override
    public Rectangle getRectangle() throws Exception {

        return new Rectangle(ZERO,
                ZERO,
                Optional.of(getWidthInPoints()).orElseThrow(() -> new Exception("Width is not set.")),
                Optional.of(getHeightInPoints()).orElseThrow(() -> new Exception("Height is not set."))
        );
    }

    @Override
    public PageSize getPageSize() throws Exception {
        return new PageSize(getRectangle());
    }
}
