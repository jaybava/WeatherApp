
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// ignore all unneeded properties
@JsonIgnoreProperties(ignoreUnknown = true)
// public class to create Geocode object which will house the longitude and latitude of the city inputted
public class Geocode {
    // basic variables
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("lat")
    private double lat;

    // getters for Geocode
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }

    // default toString method
    public String toString() {
        return lat + " " + lon;
    }
}
