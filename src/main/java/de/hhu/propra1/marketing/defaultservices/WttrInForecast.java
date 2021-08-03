package de.hhu.propra1.marketing.defaultservices;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

class CurrentCondition {
    private Integer temp_C;

    public int getTemperatureCelsius() {
        return temp_C;
    }
}

class WeatherData {
    private List<CurrentCondition> current_condition;

    public List<CurrentCondition> getCurrentCondition() {
        return current_condition;
    }
}

public class WttrInForecast {

    private static int getTemperature() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wttr.in/düsseldorf?format=j1"))
                .build();
        String weatherResponse = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        WeatherData weatherData = new Gson().fromJson(weatherResponse, WeatherData.class);
        return weatherData.getCurrentCondition().get(0).getTemperatureCelsius();
    }

    public int getCelsiusTemperature() {
        try {
            return getTemperature();
        } catch (InterruptedException | IOException e) {
            return 0;
        }
    }
}
