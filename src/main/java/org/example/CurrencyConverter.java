package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CurrencyConverter {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private String apiKey;
    private Map<String, String> currencyPairs;

    public CurrencyConverter() throws Exception {
        // Cargar la API Key desde el archivo config.properties
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("config.properties");
        properties.load(input);
        apiKey = properties.getProperty("API_KEY");
        input.close();

        currencyPairs = new HashMap<>();
        currencyPairs.put("1", "USD/ARS");
        currencyPairs.put("2", "ARS/USD");
        currencyPairs.put("3", "USD/BRL");
        currencyPairs.put("4", "BRL/USD");
        currencyPairs.put("5", "USD/COP");
        currencyPairs.put("6", "COP/USD");
    }

    public double convert(String option, double amount) throws Exception {
        String[] currencies = currencyPairs.get(option).split("/");
        String fromCurrency = currencies[0];
        String toCurrency = currencies[1];
        double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
        return amount * exchangeRate;
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String urlStr = API_URL + apiKey + "/pair/" + fromCurrency + "/" + toCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();

        if (jsonResponse.get("result").getAsString().equals("success")) {
            return jsonResponse.get("conversion_rate").getAsDouble();
        } else {
            throw new Exception("Error fetching exchange rate.");
        }
    }
}
