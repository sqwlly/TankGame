package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller.getInstance();
        // primaryStage.setScene(new Scene(root, 300, 275));
       // primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
