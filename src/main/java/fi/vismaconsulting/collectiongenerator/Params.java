package fi.vismaconsulting.collectiongenerator;

import java.util.List;

public class Params {
    private String folderPath;
    private String targetPath;
    private String attributeToBeChanged;
    private List<String> attributes;

    public Params() {
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getAttributeToBeChanged() {
        return attributeToBeChanged;
    }

    public void setAttributeToBeChanged(String attributeToBeChanged) {
        this.attributeToBeChanged = attributeToBeChanged;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
