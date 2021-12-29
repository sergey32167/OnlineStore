package com.issoft.training.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.RandomStorePopulator;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;


public class HttpServerLocal {
    private HttpServer server;

    public void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8008), 11);
            createContext("/test", new MyHandler());
            createContext("/test1", new MyHandler1());
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createContext(String endpoint, HttpHandler handler) {
        server.createContext(endpoint, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String user, String password) {
                return user.equals("user") && password.equals("password");
            }
        });
    }

    static class MyHandler implements HttpHandler {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        List<Category> categoryList = randomStorePopulator.createListCategories();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            byte[] bytesList = mapper.writeValueAsBytes(categoryList);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytesList.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytesList);
            os.close();
        }
    }

    static class MyHandler1 implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Product added to cart";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
