package no.deichman.labelpdf.no.deichman.labelpdf.utils;

/**
 * Responsibility: provide units.
 */
public enum Unit {
    MM("millimetres"),
    PT("points");

    Unit(String unit) {
        this.unit = unit;
    }
    private String unit;

    public String getUnit() {
        return this.unit;
    }
}
