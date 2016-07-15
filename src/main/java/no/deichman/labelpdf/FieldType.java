package no.deichman.labelpdf;

/**
 * Responsibility: provide field types & parameters for label fields.
 */
enum FieldType {
    TITLE(25),
    CREATOR(25),
    CALLNUMER(15);

    private final int maxlength;

    FieldType(int maxlength) {
        this.maxlength = maxlength;
    }

    public final int maxlength() {
        return maxlength;
    }
}
