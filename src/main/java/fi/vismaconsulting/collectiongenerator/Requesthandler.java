package fi.vismaconsulting.collectiongenerator;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Requesthandler {

    public void generateCollections(Params params) {
        String path = params.getFolderPath();
        File file = new File(path);
        List<File> requestFiles = listFilesForFolder(file);
        for(File file1 : requestFiles) {

        }



    }

    public List<File> listFilesForFolder(final File folder) {
        List<File> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry);
            }
        }
        return files;

    }

}
