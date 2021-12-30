package com.issoft.training.stores.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.listCategories.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddToCartCommand implements Command {
    private final String authHeaderValue;
    private String name = "add";

    public AddToCartCommand() {
        authHeaderValue = "Basic " + new String(Base64.getEncoder().encode(("user" + ":" + "password").getBytes()));
    }

    @Override
    public void execute(Store store) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            HttpURLConnection connection;
            try {
                URL url = new URL("http://localhost:8008/productCart");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Authorization", authHeaderValue);
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
            } catch (IOException e) {
                throw new RuntimeException("The address error", e);
            }
            try {
                List<Product> allProduct = new ArrayList<>(store.getAllShopProducts());
                Random random = new Random();
                int size = allProduct.size();
                Product product = allProduct.get(random.nextInt(size));

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
            } finally {
                connection.disconnect();
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }
}
