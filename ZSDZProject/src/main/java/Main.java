import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Heater> heaters = new ArrayList<>();
        heaters.add(new Heater(200, 1));

        List<Layer> layersWall1 = new ArrayList<>();
        layersWall1.add(new Layer(0.1, 0.02));

        List<Cuboid> walls = new ArrayList<>();

        List<Cuboid> windowsWall1 = new ArrayList<>();

        List<IrregularCuboid> wallsWithWindows = new ArrayList<>();
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5, windowsWall1));
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5, windowsWall1));
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5, windowsWall1));
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5, windowsWall1));
        wallsWithWindows.add(new IrregularCuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5, windowsWall1));
        Cuboid floor = new Cuboid(20 , 0, layersWall1, 0.2 , 0.2 , 5, 5);

        Room room = new Room(20, wallsWithWindows, walls, floor, heaters);

        System.out.println("Start...");

        for (int i = 0; i < 20000; i++) {
            room.calculateTemperature();
            System.out.println(room.getTemperatureInside());
        }
    }
}
