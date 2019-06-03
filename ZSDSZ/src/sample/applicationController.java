package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class applicationController {

    @FXML
    private AnchorPane AP;
    
    public void gotoTemperatureInside(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("temperatureInside.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Temperature");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoWalls(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("walls.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Walls");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoWind(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("wind.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Wind");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoSimulation(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("simulation.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Simulation");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void gotoApplication(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("application.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SDSZ");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setTemperatureInside(ActionEvent event) {
    }

    public void setWall(ActionEvent event) {
    }

    public void setWind(ActionEvent event) {
    }


}
