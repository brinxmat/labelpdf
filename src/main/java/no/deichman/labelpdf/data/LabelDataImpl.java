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
    public String getCallNumber() {
        return callNumber;
    }

    @Override
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String getHoldingBranch() {
        return holdingBranch;
    }

    @Override
    public void setHoldingBranch(String holdingBranch) {
        this.holdingBranch = holdingBranch;
    }

    @Override
    public String getBiblio() {
        return biblio;
    }

    @Override
    public void setBiblio(String biblio) {
        this.biblio = biblio;
    }

    @Override
    public String getCopyNumber() {
        return copyNumber;
    }

    @Override
    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public void setBarcode(String barcode) {
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
