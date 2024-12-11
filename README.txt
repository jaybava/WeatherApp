WeatherApp
A Java-based Weather Application using JavaFX for the user interface and OpenWeatherMap APIs for fetching real-time weather information. The app allows users to input a city, state, and country to retrieve current weather conditions.

Project Structure
.
├── src/
│   ├── ApplicationDriver.java       - Main JavaFX driver for the application
│   ├── Controller.java              - JavaFX controller handling user input and scene transitions
│   ├── Geocode.java                 - Data class for geocoding information (latitude & longitude)
│   ├── GeocodingAPIClient.java      - Client for fetching geocoding data from OpenWeatherMap
│   ├── Main.java                    - Secondary entry point
│   ├── WeatherAPIClient.java        - Client for fetching weather data from OpenWeatherMap
│   └── WeatherInfo.java             - Data class for storing weather information
│
├── resources/
│   ├── scene1.fxml                  - FXML for the initial scene (location input)
│   ├── scene2.fxml                  - FXML for the processing/loading scene
│   ├── scene3.fxml                  - FXML for displaying weather results
│   └── style.css                    - Stylesheet for JavaFX scenes
│
└── out/artifacts/
    └── WeatherApp_jar/
        └── WeatherApp.jar           - Executable JAR file

How to Run the Project
Install Java (JDK 17 or higher) and ensure JAVA_HOME is set up.

Run the JAR file located in out/artifacts/WeatherApp_jar/WeatherApp.jar:

<pre><code>```bash java -jar out/artifacts/WeatherApp_jar/WeatherApp.jar ```</code></pre>

Enter Location Details:
Provide a City, optional State, and Country.

View Weather Data:
The app displays current temperature, wind speed, pressure, and humidity.

Dependencies
JavaFX (for UI components)
Jackson Databind (for parsing JSON data)
OpenWeatherMap API:
You'll need to replace "INSERT API KEY HERE" with your own API key in:
GeocodingAPIClient.java
WeatherAPIClient.java

Features
Geolocation Lookup:
Converts city, state, and country inputs to latitude and longitude using the OpenWeatherMap Geocoding API.

Weather Data Fetching:
Retrieves current weather information based on geolocation.

User-Friendly UI:
JavaFX-based GUI with multiple scenes for input, processing, and results.

Error Handling:
Provides alerts for invalid inputs or failed API requests.

API References
Geocoding API: https://openweathermap.org/api/geocoding-api
Weather API: https://openweathermap.org/current