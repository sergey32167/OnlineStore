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

    private static void responseBody(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void createContext(String endpoint, HttpHandler handler) {
        server.createContext(endpoint, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String user, String password) {
                return user.equals("user") && password.equals("password");
            }
        });
    }

    public void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8008), 11);
            createContext("/listCategories", new HandlerGetList());
            createContext("/productCart", new HandlerAddToCart());
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class HandlerGetList implements HttpHandler {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        List<Category> categoryList = randomStorePopulator.createListCategories();
        ObjectMapper mapper = new ObjectMapper();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = mapper.writeValueAsString(categoryList);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            responseBody(exchange, response);
        }
    }

    static class HandlerAddToCart implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Product added to cart";
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "text/plain");
            responseBody(exchange, response);
        }
    }
}
