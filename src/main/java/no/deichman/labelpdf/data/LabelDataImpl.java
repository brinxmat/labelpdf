package no.deichman.labelpdf.data;

import com.google.gson.annotations.SerializedName;

/**
 * Responsibility: manage data for labels.
 */
public class LabelDataImpl implements LabelData {
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

    @Override
    public final String getCallNumber() {
        return callNumber;
    }

    @Override
    public final void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    @Override
    public final String getCreator() {
        return creator;
    }

    @Override
    public final void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final void setTitle(String title) {
        this.title = title;
    }

    @Override
    public final String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public final void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public final String getHoldingBranch() {
        return holdingBranch;
    }

    @Override
    public final void setHoldingBranch(String holdingBranch) {
        this.holdingBranch = holdingBranch;
    }

    @Override
    public final String getBiblio() {
        return biblio;
    }

    @Override
    public final void setBiblio(String biblio) {
        this.biblio = biblio;
    }

    @Override
    public final String getCopyNumber() {
        return copyNumber;
    }

    @Override
    public final void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    @Override
    public final String getBarcode() {
        return barcode;
    }

    @Override
    public final void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LabelDataImpl() {

    }

    public LabelDataImpl(String callNumber,
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

}
