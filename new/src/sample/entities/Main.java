package sample.entities;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Heater> heaters = new ArrayList<>();
        heaters.add(new Heater(12000, 1, 80, 60, 20, 1.33));

        List<Layer> layersWall1 = new ArrayList<>();
        /*brickwork*/
        layersWall1.add(new Layer(0.105, 0.77));
        /*insulation*/
        layersWall1.add(new Layer(0.05, 0.04));
        /*light concrete blockwork and mortar*/
        layersWall1.add(new Layer(0.1, 0.2128));
        /*lightweight plaster*/
        layersWall1.add(new Layer(0.013, 0.18));

        List<Layer> layersWindow1 = new ArrayList<>();
        layersWindow1.add(new Layer(0.005, 0.8));
        layersWindow1.add(new Layer(0.005, 0.024));
        layersWindow1.add(new Layer(0.005, 0.8));

        List<Cuboid> windowsWall1 = new ArrayList<>();

        windowsWall1.add(new Cuboid(20, 0, layersWindow1, 5, 1, 1));

        List<Cuboid> walls = new ArrayList<>();

        List<IrregularCuboid> wallsWithWindows = new ArrayList<>();
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 5, 3, 10, windowsWall1));
        walls.add(new Cuboid(20 , 0, layersWall1, 1, 3, 10));
        walls.add(new Cuboid(20 , 0, layersWall1, 1, 3, 10));
        walls.add(new Cuboid(20 , 0, layersWall1, 1, 3, 10));
        walls.add(new Cuboid(20 , 0, layersWall1, 0, 10, 10));
        FloorOnGround floor = new FloorOnGround(20 , 0, layersWall1, 0, 10, 10);
        Heater heater;
       // Room room = new Room(1,wallsWithWindows,walls,floor,heater);

        System.out.println("Start...");

        List<Double> temperatures = new ArrayList<>();
        temperatures.add(0.);
        temperatures.add(-5.);
        temperatures.add(-3.3);
        temperatures.add(1.);
        temperatures.add(2.5);
        temperatures.add(1.5);

        Temperature temperature = new Temperature(temperatures, 14400);

        for (int i = 0; i < 86400; i++) {
            //room.calculateTemperature(i, temperature);
            if(i%60 == 0) {
                //System.out.println(i + " " + room.getTemperatureInside());
            }
        }
    }
}
