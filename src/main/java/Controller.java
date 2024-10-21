import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

// controller for the javaFx application
public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private WeatherInfo weatherInfo;


    @FXML
    private TextField userCity;

    @FXML
    private TextField userCountry;

    @FXML
    private TextField userState;

    @FXML
    private Label currentTemp;

    @FXML
    private Label feelsLike;

    @FXML
    private Label windSpeed;

    @FXML
    private Label pressure;

    @FXML
    private Label humidity;

    @FXML
    private Label locationLabel;

    // method triggered when "START" and "BACK" buttons are pressed
    @FXML
    void begin(ActionEvent event) throws IOException {
        // load into the second scene
        root = FXMLLoader.load(getClass().getResource("/scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // use css file
        stage.setScene(scene);
        stage.show();
    }


    // method triggered when "NEXT" button is pressed
    @FXML
    void submit(ActionEvent event) throws IOException {
        // get variables from inputted text fields
        String city = userCity.getText().toLowerCase();
        String country = userCountry.getText().toLowerCase();
        String state = userState.getText().toLowerCase();

        // create required API clients
        GeocodingAPIClient geocodingAPIClient = new GeocodingAPIClient();
        WeatherAPIClient weatherAPIClient = new WeatherAPIClient();

        // use try catch to catch the IO exception or invalid input
        try {
            // pass the city, state, and country to the GeocodingAPI
            Geocode geocode = geocodingAPIClient.getGeocoding(city, state, country);

            // filter is Geocode is not retrieved properly
            if(geocode != null){
                // use geocode to get WeatherInfo
                this.weatherInfo = weatherAPIClient.getWeatherInfo(geocode.getLat(), geocode.getLon());
            }else {
                throw new IOException();
            }
        } catch (Exception e){
            // if input is invalid showcase an error message to user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Input is Invalid");
            alert.setContentText("Please check all your fields and try again.");

            alert.showAndWait();
        }

        // prepare the third scene to be loaded
        root = FXMLLoader.load(getClass().getResource("/scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // assign the labels to their id
        currentTemp =(Label) scene.lookup("#currentTemp");
        feelsLike =(Label) scene.lookup("#feelsLike");
        windSpeed =(Label) scene.lookup("#windSpeed");
        pressure =(Label) scene.lookup("#pressure");
        humidity =(Label) scene.lookup("#humidity");
        locationLabel =(Label) scene.lookup("#locationLabel");

        // populate variables with weatherInfo variables
        currentTemp.setText(Math.round(weatherInfo.getTemp()) + "°C");
        feelsLike.setText(Math.round(weatherInfo.getFeelsLike()) + "°C");
        windSpeed.setText(Math.round(weatherInfo.getWindSpeed() * 3.6)+ " kM/H");
        pressure.setText(Math.round(weatherInfo.getPressure() * 0.1)+ " kPa");
        humidity.setText(weatherInfo.getHumidity() + "%");

        // filter the text for locationLabel if it is empty
        if (state.isEmpty()){
            locationLabel.setText(city + ", " + country);
        } else {
            locationLabel.setText(city + ", " + state + ", " + country);
        }

        // display scene
        stage.setScene(scene);
        stage.show();


    }


    // method triggered when "EXIT" button is pressed
    @FXML
    void exit(ActionEvent event) {
        // exit the program
        Platform.exit();
        System.exit(0);
    }


}
