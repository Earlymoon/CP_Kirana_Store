package com.example.kirana.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConversionService {
     private final String CURRENCY_CONVERT_URL=
             "https://api.fxratesapi.com/latest?base=";

    public Double convertCurrency(Double amount, String to) {
        RestTemplate restTemplate = new RestTemplate();
        String url = CURRENCY_CONVERT_URL + to;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.get("rates") instanceof Map) {
            Map<String, Double> rateChart = (Map<String, Double>) response.get("rates");
            double rate = rateChart.getOrDefault("INR", 1.0);


            return amount*rate;
        }

        return amount; // Return the original amount if there is an issue
    }

}
