package no.deichman.labelpdf.data;

/**
 * Responsibility: provide label data.
 */
public interface LabelData {
    String getCallNumber();

    void setCallNumber(String callNumber);

    String getCreator();

    void setCreator(String creator);

    String getTitle();

    void setTitle(String title);

    String getPublicationDate();

    void setPublicationDate(String publicationDate);

    String getHoldingBranch();

    void setHoldingBranch(String holdingBranch);

    String getBiblio();

    void setBiblio(String biblio);

    String getCopyNumber();

    void setCopyNumber(String copyNumber);

    String getBarcode();

    void setBarcode(String barcode);
}
