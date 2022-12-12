package com.example.businessdiscovery.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {

    public static HttpRequest createInstance() {
        return new HttpRequest();
    }

    private HttpRequest() {
    }

    public Map<String, Object> execute(String targetUrl) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(output.toString(), new TypeReference<Map<String, Object>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
