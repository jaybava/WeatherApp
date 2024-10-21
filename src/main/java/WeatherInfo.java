import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
// public class to store all the weather info given by api
public class WeatherInfo {

    // variables for WeatherInfo
    @JsonProperty("temp")
    private float temp;

    @JsonProperty("feels_like")
    private float feelsLike;

    private float windSpeed;

    @JsonProperty("pressure")
    private float pressure;

    @JsonProperty("humidity")
    private int humidity;



    // getters for the WeatherInfo
    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    // setter for setting windSpeed
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    // default toString method
    @Override
    public String toString() {
        return "Temperature: " + temp + "°C, Feels Like: " + feelsLike + "°C, " +
                "Wind Speed: " + windSpeed + "km/h, Pressure: " + pressure + "°C, " +
                "Humidity: " + humidity + "%";
    }
}
