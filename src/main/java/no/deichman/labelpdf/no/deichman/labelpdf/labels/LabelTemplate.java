package no.deichman.labelpdf.no.deichman.labelpdf.labels;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;

/**
 * Responsibility: provide interface for LabelTemplate class.
 */
public interface LabelTemplate {
    void setWidthInMM(double widthInMM);
    double getWidthInMM();
    void setHeightInMM(double heightInMM);
    double getHeightInMM();
    void setWidthInPoints(int widthInPoints);
    int getWidthInPoints();
    void setHeightInPoints(int heightInPoints);
    int getHeightInPoints();
    Rectangle getRectangle() throws Exception;
    PageSize getPageSize() throws Exception;
}
