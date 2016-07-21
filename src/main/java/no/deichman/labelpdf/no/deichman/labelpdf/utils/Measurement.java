package no.deichman.labelpdf.no.deichman.labelpdf.utils;

/**
 * Responsibility: provide unit conversions.
 */
public class Measurement {

    private static final double ONE_POINT_IN_MILLIMETRES = 0.352777778;
    private static final double ONE_MILLIMETRE_IN_POINTS = 2.83465;
    private static final int ONE_HUNDRED = 100;
    private Unit unit;
    private double size;

    private void setUnit(Unit unit) {
        this.unit = unit;
    }

    private Unit getUnit() {
        return this.unit;
    }

    private void setSize(double size, Unit unit) {
        setUnit(unit);
        this.size = size;
    }

    private double getSize() {
        return this.size;
    }

    public final void setSizeInMillimetres(double size) {
        setSize(size, Unit.MM);
    }

    public final void setSizeInPoints(int size) {
        setSize(size, Unit.PT);
    }

    public final double getSizeInMillimetres() {
        //two decimal places
        double retVal = getSize();

        switch (getUnit()) {
            case PT: retVal = getSize() * ONE_POINT_IN_MILLIMETRES;
                break;
            case MM:
            default: //NOOP
                break;
        }
        return roundTwoDecimalPlaces(retVal);
    }

    public final int getSizeInPoints() {
        //Note: this will always *round down* to nearest point
        int retVal = (int) getSize();

        switch (getUnit()) {
            case MM: retVal = (int) (getSize() * ONE_MILLIMETRE_IN_POINTS);
                break;
            case PT:
            default:
                break;
        }
        return retVal;
    }

    private double roundTwoDecimalPlaces(double input) {
        return (double) Math.round(input * ONE_HUNDRED) / ONE_HUNDRED;
    }
}
