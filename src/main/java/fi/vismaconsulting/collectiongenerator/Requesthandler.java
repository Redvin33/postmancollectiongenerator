package fi.vismaconsulting.collectiongenerator;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Requesthandler {



    public void generateCollections(Params params) {
        try {
            String content = getFileContentAsString(params.getFolderPath());
            JSONObject jsonObject = new JSONObject(content);
            generateCollectionsWithNewUrl(params.getAttributes(), jsonObject);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private void generateCollectionsWithNewUrl(String urlString, JSONObject content) {

        try {
            URL url = new URL(urlString);
            String paths = url.getPath();
            String query = url.getQuery();
            //String[] queryparams = query.split("");
            String[] pathParams = paths.substring(1).split("/");

            JSONArray requests = content.getJSONArray("item");
            JSONArray pathsArray = new JSONArray();
            for(int i = 0; i < pathParams.length; i++) {
                pathsArray.put(pathParams[i]);
            }

            for (int i = 0; i < requests.length(); i++) {
                JSONObject obj = requests.getJSONObject(i).getJSONObject("request").getJSONObject("url");
                obj.put("raw", url);
                obj.put("path", pathsArray);
                System.out.println(obj);
                System.out.println(content);
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }




}
