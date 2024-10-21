
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// driver for the javaFX program
public class ApplicationDriver extends Application {
    // start method when starting the program
    @Override
    public void start(Stage stage) throws Exception {
        try{
            // load into the first scene
            Parent root = FXMLLoader.load(getClass().getResource("/scene1.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // launch the program
    public static void main(String[] args) {launch();}
}
