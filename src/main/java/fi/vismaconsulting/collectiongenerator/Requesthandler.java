package fi.vismaconsulting.collectiongenerator;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Requesthandler {

    public void generateCollections(Params params) {
        try {
            String content = getFileContentAsString(params.getFolderPath());
            JSONObject jsonObject = new JSONObject(content);
        } catch (Exception e) {

        }



    }

    public String getFileContentAsString(String path) {
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();

            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            System.out.println(fileAsString);
            return fileAsString;

        } catch (Exception e) {
            return null;
        }

    }


    public void convertJSONObjectToFile(JSONObject json, String target) throws Exception {

        String collectionName = json.getJSONObject("info").getString("name");

        if(collectionName.charAt(collectionName.length() -1) != '/') {
            collectionName = "/" + collectionName + ".json";
        } else {
            collectionName = collectionName + ".json";
        }

        Path p = Paths.get(target + collectionName);
        BufferedWriter writer = Files.newBufferedWriter(p);
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }





}
