package no.deichman.labelpdf.fontutils;

/**
 * Responsibility: provide hooks for font resource management.
 */
public enum Font {
    SERIF("/resources/font/FreeSerif.otf"),
    SANS("/resources/font/FreeSans.otf");

    private final String path;

    Font(String path) {
        this.path = path;
    }

    public final String getPath() {
        return this.path;
    }
}
