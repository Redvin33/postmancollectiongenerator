package fi.vismaconsulting.collectiongenerator;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
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

    private String getFileContentAsString(String path) {
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



}
