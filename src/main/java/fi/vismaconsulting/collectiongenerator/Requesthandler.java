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
            for(String param : params.getAttributes()) {
                JSONObject obj = generateCollectionsWithNewUrl(param, jsonObject);
                convertJSONObjectToFile(obj, params.getTargetPath());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private JSONObject generateCollectionsWithNewUrl(String urlString, JSONObject content) {
        try {
            URL url = new URL(urlString);
            String protocol = url.getProtocol();
            String[] hosts = url.getHost().split("\\.");

            String paths = url.getPath();
            String query = url.getQuery();
            //String[] queryparams = query.split("");
            String[] pathParams = paths.substring(1).split("/");

            JSONArray requests = content.getJSONArray("item");
            JSONArray pathsArray = new JSONArray();
            JSONArray hostsArray = new JSONArray();
            for(int i = 0; i < pathParams.length; i++) {
                pathsArray.put(pathParams[i]);
            }

            for(int i = 0; i< hosts.length; i++) {
                hostsArray.put(hosts[i]);
            }

            for (int i = 0; i < requests.length(); i++) {
                JSONObject obj = requests.getJSONObject(i).getJSONObject("request").getJSONObject("url");
                obj.put("raw", url);
                obj.put("path", pathsArray);
                obj.put("hosts", hostsArray);

            }
            System.out.println(content);

            return content;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return content;
        }

    }


    public void convertJSONObjectToFile(JSONObject json, String target) throws Exception {

        String collectionName = json.getJSONObject("info").getString("name");

        collectionName = collectionName + ".json";

        Path p = Paths.get(target + collectionName);
        BufferedWriter writer = Files.newBufferedWriter(p);
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }





}
