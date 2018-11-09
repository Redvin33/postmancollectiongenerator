package fi.vismaconsulting.collectiongenerator;

public class Params {
    private String folderPath;
    private String attributeToBeChanged;
    private String attributes;

    public Params() {
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getAttributeToBeChanged() {
        return attributeToBeChanged;
    }

    public void setAttributeToBeChanged(String attributeToBeChanged) {
        this.attributeToBeChanged = attributeToBeChanged;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
