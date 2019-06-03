package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class applicationController {

    public Heater heater;
    public Double tempInside;
    public List<Layer> layersWall;
    public List<Layer> layersWindow;
    public List<Cuboid> windowsWall;
    public List<Cuboid> walls;
    public List<IrregularCuboid> wallsWithWindows;
    public List<Double> temperatures;
    public FloorOnGround floor;
    public Room room;

    public TextField temperatureInside;
    public TextField heaterPower;
    public TextField heaterVolume;
    public TextField heaterSurroundingAirTemperature;
    public TextField wallThickness1;
    public TextField wallConductivity1;
    public TextField wallThickness2;
    public TextField wallConductivity2;
    public TextField wallThickness3;
    public TextField wallConductivity3;
    public TextField wallThickness4;
    public TextField wallConductivity4;
    public CheckBox wallWindow;
    public TextField windowWidth;
    public TextField windowHeight;
    public TextField windowThickness1;
    public TextField windowConductivity1;
    public TextField temperatureOutside;
    public TextField wallWidth;
    public TextField wallHeight;
    public TextField windVelocity;
    public TextField floorWidth;
    public TextField floorHeight;
    public TextField floorThickness1;
    public TextField floorConductivity1;
    public TextField floorThickness2;
    public TextField floorConductivity2;
    public Button exitButt;
    @FXML
    private AnchorPane AP;
    private int counter = 5;


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

            room = new Room(tempInside, wallsWithWindows, walls, floor, heater);

            List<Double> temperatures = new ArrayList<>();
            temperatures.add(0.);
            temperatures.add(-5.);
            temperatures.add(-3.3);
            temperatures.add(1.);
            temperatures.add(2.5);
            temperatures.add(1.5);

            Temperature temperature = new Temperature(temperatures, 14400);

            for (int i = 0; i < 86400; i++) {
                room.calculateTemperature(i, temperature);
                if (i % 60 == 0) {
                    System.out.println(i + " " + room.getTemperatureInside());
                }
            }


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

    public void gotoFloor(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("floor.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Floor");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void gotoHeater(ActionEvent event) {
        Stage primaryStage = (Stage) AP.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("heater.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Heater");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTemperatureInside(ActionEvent event) {
        tempInside = Double.valueOf(temperatureInside.getText());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success!");
        a.setHeaderText("Temperature inside: ");
        a.setContentText("Temperature: " + this.tempInside);
        a.show();
    }

    public void setWall(ActionEvent event) {
        if(counter>0) {
            counter -= 1;
            layersWall = new ArrayList<Layer>();
            this.layersWall.add(new Layer(Double.parseDouble(wallThickness1.getText()), Double.parseDouble(wallConductivity1.getText())));
            this.layersWall.add(new Layer(Double.parseDouble(wallThickness2.getText()), Double.parseDouble(wallConductivity2.getText())));
            this.layersWall.add(new Layer(Double.parseDouble(wallThickness3.getText()), Double.parseDouble(wallConductivity3.getText())));
            this.layersWall.add(new Layer(Double.parseDouble(wallThickness4.getText()), Double.parseDouble(wallConductivity4.getText())));
            System.out.print(tempInside);
            if (wallWindow.isSelected()) {
                this.layersWindow.add(new Layer(Double.parseDouble(windowThickness1.getText()), Double.parseDouble(windowConductivity1.getText())));
                this.windowsWall.add(new Cuboid(this.tempInside, Double.parseDouble(temperatureOutside.getText()), layersWindow, 1, Double.parseDouble(windowHeight.getText()), Double.parseDouble(windowWidth.getText())));
                this.wallsWithWindows.add(new IrregularCuboid(tempInside, Double.parseDouble(temperatureOutside.getText()), layersWall, 5, 3, 10, windowsWall));
            } else {
                this.walls.add(new Cuboid(tempInside, Double.parseDouble(temperatureOutside.getText()), layersWall, 1, Double.parseDouble(wallHeight.getText()), Double.parseDouble(wallWidth.getText())));
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success!");
            a.setHeaderText("Walls:");
            a.setContentText("Wall added!");
            a.show();
        }
        else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning!");
            a.setHeaderText("Walls:");
            a.setContentText("Too many walls!");
            a.show();

        }
    }

    public void setWind(ActionEvent event) {


    }


    public void setFloor(ActionEvent event) {
        this.floor = new FloorOnGround(tempInside , 0, layersWall, 0, Double.parseDouble(floorHeight.getText()),Double.parseDouble(floorWidth.getText()));

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success!");
        a.setHeaderText("Floor:");
        a.setContentText("Floor added!");
        a.show();
    }

    public void setHeater(ActionEvent event) {
        double power = Double.parseDouble(heaterPower.getText());
        double volume = Double.parseDouble(heaterVolume.getText());
        double surroundingAirTemperature = Double.parseDouble(heaterSurroundingAirTemperature.getText());
        this.heater = new Heater(power, volume, surroundingAirTemperature);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success!");
        a.setHeaderText("Heater:");
        a.setContentText("Heater succesfully added!");
        a.show();
    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitButt.getScene().getWindow();
        stage.close();
    }
}
