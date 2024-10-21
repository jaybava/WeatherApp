import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// api client to handle conversion from city, state and country inputs to longitude and latitude for WeatherAPI to handle
public class GeocodingAPIClient {

    private static final String GEOCODING_API_URL = "https://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String API_KEY = ""; // replace api key with one from openweathermap

    public Geocode getGeocoding(String city, String state, String country) throws IOException, InterruptedException {
        String url;
        // create string based on given city and country or given city, state and country
        if (state.isEmpty()){
            url = GEOCODING_API_URL + city + "," + country + "&appid=" + API_KEY;
        }else {
            url = GEOCODING_API_URL + city + "," + state + "," + country + "&appid=" + API_KEY;
        }
        return makeAPIRequest(url);
    }

    private Geocode makeAPIRequest(String url) throws IOException, InterruptedException {
        // create HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // create a HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .GET() // signal we are getting information
                .header("accept", "application/json") // accept a json files
                .uri(URI.create(url)) // create a uri from url
                .build(); // build the request
        // send the HttpRequest
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // create ObjectMapper to map information
        ObjectMapper mapper = new ObjectMapper();

        // create the root node for parsing json
        JsonNode root = mapper.readTree(response.body());

        // check to make sure we got information
        if(!root.isEmpty()){
            // get first element in array under variable firstNode
            JsonNode firstNode = root.get(0);
            // map firstNode to Geocode class using treeToValue method
            return mapper.treeToValue(firstNode, Geocode.class);
        }
        else {
            // if geocode is empty
            throw new IOException("No geocode data found");
        }
    }

}
