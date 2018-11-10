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
import java.util.*;

@Component
public class Requesthandler {
    static int number = 0;

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
            List<KeyValue> queryParams = getMapFromUrlQuery(query);
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
                if(pathsArray.length() > 0) {
                    obj.put("path", pathsArray);
                }
                if(hostsArray.length() > 0 ) {
                    obj.put("host", hostsArray);
                }
                if(queryParams.size() > 0 ) {
                    JSONArray queryArray = new JSONArray();
                    for(KeyValue kv : queryParams) {
                        queryArray.put(new JSONObject(kv));
                    }
                    obj.put("query", queryArray);
                }
                obj.put("protocol", protocol);

            }
            System.out.println(content);
            return content;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return content;
        }

    }


    private List<KeyValue> getMapFromUrlQuery(String query) {
        String[] pairs = query.split("&");
        List<KeyValue> queryParamMap = new ArrayList<>();
        for(String pair : pairs) {
            String[] keyValuePair = pair.split("=");
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(keyValuePair[0]);
            keyValue.setValue(keyValuePair[1]);
            queryParamMap.add(keyValue);
        }
        return queryParamMap;

    }

    public void convertJSONObjectToFile(JSONObject json, String target) throws Exception {

        String collectionName = json.getJSONObject("info").getString("name") + number;
        number++;
        collectionName = collectionName + ".json";
        Path p = Paths.get(target + collectionName);
        BufferedWriter writer = Files.newBufferedWriter(p);
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }





}
