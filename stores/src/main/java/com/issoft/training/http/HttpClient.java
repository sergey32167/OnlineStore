package com.issoft.training.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.Populator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class HttpClient implements Populator {
    private HttpURLConnection connection;

    @Override
    public List<Category> createListCategories() {
        try {
            getConnection("http://localhost:8008/test");
            connection.setRequestMethod("GET");
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
                return mapper.readValue(stringBuilder.toString(), new TypeReference<List<Category>>() {
                });
            }

        } catch (ProtocolException e) {
            throw new RuntimeException("Mismatches of data transfer protocols", e);
        } catch (IOException e) {
            throw new RuntimeException("The address error", e);
        }
        connection.disconnect();
        return null;
    }

    public void addProductToCart(List<Product> allShopProduct) {
        try {
            getConnection("http://localhost:8008/test1");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            Random random = new Random();
            int size = allShopProduct.size();
            Product product = allShopProduct.get(random.nextInt(size));

            ObjectMapper mapper = new ObjectMapper();
            byte[] data = mapper.writeValueAsBytes(product);
            OutputStream os = connection.getOutputStream();
            os.write(data);

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                InputStreamReader ipsR = new InputStreamReader(connection.getInputStream());
                BufferedReader bfR = new BufferedReader(ipsR);
                String line;
                while ((line = bfR.readLine()) != null)
                    System.out.println(line);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON content", e);
        } catch (IOException e) {
            throw new RuntimeException("The address error", e);
        }
        connection.disconnect();
    }

    private void getConnection(String endpoint) {
        String user = "user";
        String password = "password";
        try {
            URL url = new URL(endpoint);
            String auth = user + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
            String authHeaderValue = "Basic " + new String(encodedAuth);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authHeaderValue);
        } catch (IOException e) {
            throw new RuntimeException("The address error", e);
        }
    }
}
