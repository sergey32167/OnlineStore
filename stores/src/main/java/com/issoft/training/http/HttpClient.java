package com.issoft.training.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.Populator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class HttpClient implements Populator {
    private final String authHeaderValue;
    private List<Category> categoryList;

    public HttpClient() {
        authHeaderValue = "Basic " + new String(Base64.getEncoder().encode(("user" + ":" + "password").getBytes()));
    }

    @Override
    public List<Category> createListCategories() {
        HttpURLConnection connection = getConnection("http://localhost:8008/listCategories", "GET");
        try {
            connection.setRequestProperty("Content-Type", "application/json");

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                InputStreamReader ipsR = new InputStreamReader(connection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bfR = new BufferedReader(ipsR);
                String line;
                while ((line = bfR.readLine()) != null) {
                    stringBuilder.append(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                categoryList = mapper.readValue(stringBuilder.toString(), new TypeReference<List<Category>>() {
                });
            }

        } catch (ProtocolException e) {
            throw new RuntimeException("Mismatches of data transfer protocols", e);
        } catch (IOException e) {
            throw new RuntimeException("The address error", e);
        } finally {
            connection.disconnect();
        }
        return categoryList;
    }

    private HttpURLConnection getConnection(String endpoint, String method) {
        HttpURLConnection connection;
        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authHeaderValue);
            connection.setRequestMethod(method);
        } catch (IOException e) {
            throw new RuntimeException("The address error", e);
        }
        return connection;
    }
}
