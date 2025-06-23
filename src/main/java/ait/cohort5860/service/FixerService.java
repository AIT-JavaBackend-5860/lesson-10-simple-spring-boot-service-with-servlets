package ait.cohort5860.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FixerService {
    private final String API_KEY = "FH16Plym7dFkpKz7wtdcR0Khf8uUPMR8";

    public double convert(String from, String to, double amount) {
        String url = String.format(
                "https://api.apilayer.com/fixer/convert?from=%s&to=%s&amount=%f",
                from, to, amount
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("apikey", API_KEY)
                .GET()
                .build();

        try {
            // Client initialisation
            HttpClient client = HttpClient.newHttpClient();

            // Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());

            return json.get("result").asDouble();
        } catch (Exception e) {
            System.err.println("Fixer API error: " + e.getMessage());
            return -1;
        }
    }


}
