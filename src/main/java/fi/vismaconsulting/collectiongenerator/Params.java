package fi.vismaconsulting.collectiongenerator;

public class Params {
    String folderPath;
    String attributeToBeChanged;
    String newAttribute;

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
        return newAttribute;
    }

    public void setAttributes(String attributes) {
        this.newAttribute = attributes;
    }
}
