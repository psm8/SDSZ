package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.entities.*;
import sample.entities.Temperature;

import java.util.ArrayList;
import java.util.List;

public class applicationController {

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
    public TextField windowThickness1;
    public TextField windowConductivity1;
    public TextField floorWidth;
    public TextField floorHeight;
    public TextField floorThickness1;
    public TextField floorConductivity1;
    public TextField floorThickness2;
    public TextField floorConductivity2;
    public Button exitButt;
    public TextField floorThickness3;
    public TextField floorConductivity3;
    public TextField floorThickness4;
    public TextField floorConductivity4;
    public TextField wallWidth1;
    public TextField wallHeight1;
    public CheckBox wallWindow1;
    public TextField windowWidth1;
    public TextField windowHeight1;
    public TextField temperatureOutside1;
    public TextField wallWidth2;
    public TextField wallHeight2;
    public CheckBox wallWindow2;
    public TextField windowWidth2;
    public TextField windowHeight2;
    public TextField windowThickness2;
    public TextField windowConductivity2;
    public TextField temperatureOutside2;
    public TextField wallWidth3;
    public TextField wallHeight3;
    public CheckBox wallWindow3;
    public TextField windowWidth3;
    public TextField windowHeight3;
    public TextField windowThickness3;
    public TextField windowConductivity3;
    public TextField temperatureOutside3;
    public TextField wallWidth4;
    public TextField wallHeight4;
    public CheckBox wallWindow4;
    public TextField windowWidth4;
    public TextField windowHeight4;
    public TextField windowThickness4;
    public TextField windowConductivity4;
    public TextField temperatureOutside4;

    public AnchorPane AP;


    public void setDefault(ActionEvent event){
        temperatureInside.setText("20.0");

        heaterPower.setText("8000.0");
        heaterVolume.setText("1.0");
        heaterSurroundingAirTemperature.setText("20.0");

        wallThickness1.setText("0.105");
        wallConductivity1.setText("0.77");
        wallThickness2.setText("0.05");
        wallConductivity2.setText("0.04");
        wallThickness3.setText("0.1");
        wallConductivity3.setText("0.2128");
        wallThickness4.setText("0.013");
        wallConductivity4.setText("0.18");

        wallWindow1.setSelected(true);
        windowHeight1.setText("0.5");
        windowWidth1.setText("1.0");
        windowThickness1.setText("0.005");
        windowConductivity1.setText("0.8");
        wallWindow3.setSelected(true);
        windowHeight3.setText("1.0");
        windowWidth3.setText("0.75");
        windowThickness3.setText("0.005");
        windowConductivity3.setText("0.024");

        temperatureOutside1.setText("0.0");
        temperatureOutside2.setText("1.0");
        temperatureOutside3.setText("1.0");
        temperatureOutside4.setText("1.0");

        wallHeight1.setText("2.0");
        wallWidth1.setText("10.0");
        wallHeight2.setText("2.0");
        wallWidth2.setText("8.0");
        wallHeight3.setText("2.0");
        wallWidth3.setText("10.0");
        wallHeight4.setText("2.0");
        wallWidth4.setText("8.0");

        floorHeight.setText("10.0");
        floorWidth.setText("8.0");
        floorThickness1.setText("0.105");
        floorConductivity1.setText("0.77");
        floorThickness2.setText("0.05");
        floorConductivity2.setText("0.04");
        floorThickness3.setText("0.1");
        floorConductivity3.setText("0.2128");
        floorThickness4.setText("0.013");
        floorConductivity4.setText("0.18");

    }
    
    public void simulate(ActionEvent event) {
        List<Heater> heaters = new ArrayList<>();
        heaters.add(new Heater(Double.parseDouble(heaterPower.getText()),Double.parseDouble(heaterVolume.getText()), 80, 60, Double.parseDouble(heaterSurroundingAirTemperature.getText()), 1.33));

        List<Layer> layersWall1 = new ArrayList<>();
        /*brickwork*/
        layersWall1.add(new Layer(Double.parseDouble(wallThickness1.getText()), Double.parseDouble(wallConductivity1.getText())));
        /*insulation*/
        layersWall1.add(new Layer(Double.parseDouble(wallThickness2.getText()), Double.parseDouble(wallConductivity2.getText())));
        /*light concrete blockwork and mortar*/
        layersWall1.add(new Layer(Double.parseDouble(wallThickness3.getText()), Double.parseDouble(wallConductivity3.getText())));
        /*lightweight plaster*/
        layersWall1.add(new Layer(Double.parseDouble(wallThickness4.getText()), Double.parseDouble(wallConductivity4.getText())));

        List<Layer> layersWindow1 = new ArrayList<>();
        if(wallWindow1.isSelected()) {
            layersWindow1.add(new Layer(Double.parseDouble(windowThickness1.getText()), Double.parseDouble(windowConductivity1.getText())));
        }
        if(wallWindow2.isSelected()) {
            layersWindow1.add(new Layer(Double.parseDouble(windowThickness2.getText()), Double.parseDouble(windowConductivity2.getText())));
        }
        if(wallWindow3.isSelected()) {
            layersWindow1.add(new Layer(Double.parseDouble(windowThickness3.getText()), Double.parseDouble(windowConductivity3.getText())));
        }
        if(wallWindow4.isSelected()) {
            layersWindow1.add(new Layer(Double.parseDouble(windowThickness4.getText()), Double.parseDouble(windowConductivity4.getText())));
        }

        List<Cuboid> windowsWall1 = new ArrayList<>();

        windowsWall1.add(new Cuboid(Double.parseDouble(temperatureInside.getText()), Double.parseDouble(temperatureOutside1.getText()), layersWindow1, 5, Double.parseDouble(windowHeight1.getText()), Double.parseDouble(windowWidth1.getText())));

        List<Cuboid> walls = new ArrayList<>();

        List<IrregularCuboid> wallsWithWindows = new ArrayList<>();
        wallsWithWindows.add(new IrregularCuboid(Double.parseDouble(temperatureInside.getText()), Double.parseDouble(temperatureOutside1.getText()), layersWall1, 5, Double.parseDouble(windowHeight1.getText()), Double.parseDouble(windowWidth1.getText()), windowsWall1));
        walls.add(new Cuboid(Double.parseDouble(temperatureInside.getText()) , Double.parseDouble(temperatureOutside1.getText()), layersWall1, 1, Double.parseDouble(wallHeight1.getText()), Double.parseDouble(wallWidth1.getText())));
        walls.add(new Cuboid(Double.parseDouble(temperatureInside.getText()) , Double.parseDouble(temperatureOutside2.getText()), layersWall1, 1, Double.parseDouble(wallHeight2.getText()), Double.parseDouble(wallWidth2.getText())));
        walls.add(new Cuboid(Double.parseDouble(temperatureInside.getText()) , Double.parseDouble(temperatureOutside3.getText()), layersWall1, 1, Double.parseDouble(wallHeight3.getText()), Double.parseDouble(wallWidth3.getText())));
        walls.add(new Cuboid(Double.parseDouble(temperatureInside.getText()) , Double.parseDouble(temperatureOutside4.getText()), layersWall1, 0, Double.parseDouble(wallHeight4.getText()), Double.parseDouble(wallWidth4.getText())));
        FloorOnGround floor = new FloorOnGround(Double.parseDouble(temperatureInside.getText()) , Double.parseDouble(temperatureOutside1.getText()), layersWall1, 0, Double.parseDouble(floorHeight.getText()), Double.parseDouble(floorWidth.getText()));
         Room room = new Room(Double.parseDouble(temperatureInside.getText()),wallsWithWindows,walls,floor,heaters);

        System.out.println("Start...");

        List<Double> temperatures = new ArrayList<>();
        temperatures.add(0.);
        temperatures.add(-5.);
        temperatures.add(-3.3);
        temperatures.add(1.);
        temperatures.add(2.5);
        temperatures.add(1.5);

        sample.entities.Temperature temperature = new Temperature(temperatures, 14400);

        Stage primaryStage = (Stage) AP.getScene().getWindow();

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Room heating.");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Temperature inside the room");

        for (int i = 0; i < 86400; i++) {
            room.calculateTemperature(i, temperature);
            if (i % 60 == 0) {
                System.out.println(i + " " + room.getTemperatureInside());
                series.getData().add(new XYChart.Data(i, room.getTemperatureInside()));
            }
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitButt.getScene().getWindow();
        stage.close();
    }


}
