import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// api client to use the geocode's latitude and longitude to fetch weather information
public class WeatherAPIClient {
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "";

    // method to retrieve information
    public WeatherInfo getWeatherInfo(double latitude, double longitude) throws IOException, InterruptedException {
        String url = WEATHER_API_URL + "lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&units=metric";
        return makeAPIRequest(url);
    }

    private WeatherInfo makeAPIRequest(String url) throws IOException, InterruptedException {
        // show url to console
        System.out.println("Making request to " + url);
        // same as GeocodingAPIClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        // map all the information needed from root to weatherInfo
        WeatherInfo weatherInfo = mapper.readValue(root.get("main").toString(), WeatherInfo.class);

        // access the windNode
        JsonNode windNode = root.get("wind");

        //assign wind speed value from windNode
        float windSpeed = windNode.get("speed").floatValue();
        weatherInfo.setWindSpeed(windSpeed);

        return weatherInfo;
    }
}
