import java.util.List;

public class Room {
    /*specific heat capacity of air ~= 1005 J/(kg * K); accuracy 0.2%*/
    final int SPECIFIC_HEAT_CAPACITY_OF_AIR = 1005;
    final int SPECIFIC_HEAT_CAPACITY_OF_WALL = 880;
    final int DENSITY_OF_WALL = 1920;
    /*air pressure  = 101325 Pa*/
    final int AIR_PRESSURE = 101325;
    /*specific gas constant  for dry air = 287.05 J/(kg*K)*/
    final double SPECIFIC_GAS_CONSTANT_OF_DRY_AIR = 287.05;
    double temperatureInside;
    private final List<IrregularCuboid> wallsWithWindows;
    private final List<Cuboid> walls;
    private final Cuboid floor;
    /*humans could be also heaters*/
    private final List<Heater> heaters;

    Room(int temperatureInside, List<IrregularCuboid> wallsWithWindows, List<Cuboid> walls, Cuboid floor, List<Heater> heaters){
        this.temperatureInside = temperatureInside;
        this.wallsWithWindows = wallsWithWindows;
        this.walls = walls;
        this.floor = floor;
        this.heaters = heaters;
    }

    public double getTemperatureInside() {
        return temperatureInside;
    }

    void calculateTemperature(int currentTime, Temperature temperature){

        double currentTemperatureOutside = temperature.getTemperature().get(
                (int)(currentTime/temperature.getMeasurementsInterval()));

        /* dT = Q/(m * c)
           T - temperature change (K)
           Q - heat exchanged
           m - mass (kg)
           c - specific heat capacity J/(kg * K)*/

        /*depends on temperature inside, so must be calculated before*/
        double airDensity = calculateAirDensity();

        for(Heater heater : heaters) {
            temperatureInside += heater.getPower()/
                    ((calculateVolume() * airDensity  * SPECIFIC_HEAT_CAPACITY_OF_AIR) + calculateWallHeatLoss());
        }

        for(IrregularCuboid wallWithWindows : wallsWithWindows) {

            temperatureInside += wallWithWindows.calculatHeatTransfer()/
                    (calculateVolume() * airDensity  * SPECIFIC_HEAT_CAPACITY_OF_AIR - calculateWallHeatLoss());

            for(Cuboid window : wallWithWindows.getWindows()){
                temperatureInside += window.calculatHeatTransfer()/
                        (calculateVolume() * airDensity * SPECIFIC_HEAT_CAPACITY_OF_AIR - calculateWallHeatLoss());
            }
        }


        for(Cuboid wall : walls) {
            temperatureInside += wall.calculatHeatTransfer() /
                    (calculateVolume() * airDensity * SPECIFIC_HEAT_CAPACITY_OF_AIR - calculateWallHeatLoss());
        }

            temperatureInside += floor.calculatHeatTransfer()/
                    (calculateVolume() * airDensity * SPECIFIC_HEAT_CAPACITY_OF_AIR - calculateWallHeatLoss());

        for(Heater heater : heaters) {
            heater.setSurroundingAirTemperature(temperatureInside);
        }

        for(IrregularCuboid wallWithWindows : wallsWithWindows) {

            wallWithWindows.setTemperatureInside(temperatureInside);
            wallWithWindows.setTemperatureOutside(currentTemperatureOutside);

            for (Cuboid window : wallWithWindows.getWindows()) {
                window.setTemperatureInside(temperatureInside);
                window.setTemperatureOutside(currentTemperatureOutside);
            }
        }

        for (Cuboid wall : walls) {
            wall.setTemperatureInside(temperatureInside);
            wall.setTemperatureOutside(currentTemperatureOutside);
        }

        floor.setTemperatureInside(temperatureInside);
        floor.setTemperatureOutside(currentTemperatureOutside);
    }

    private double calculateVolume(){
        /*room is rectangle*/
        double base = floor.getHeight() * floor.getWidth();
        /*all windows have same height*/
        double volume = base * wallsWithWindows.get(0).getHeight();

        double heatersVolume = 0;
        for(Heater heater : heaters){
            heatersVolume += heater.getVolume();
        }

        return volume - heatersVolume;
    }

    private double calculateAirDensity(){

        /* ro = p/(R * T)
           ro - density of dry air (kg/m^3)
           p - air pressure (Pa)
           R - specific gas constant for dry air, 287.05 J/(kg*K)
           T - temperature (K)*/

        return AIR_PRESSURE/(SPECIFIC_GAS_CONSTANT_OF_DRY_AIR * (temperatureInside + 273.15));
    }

    private double calculateWallHeatLoss(){

        double overallHeatLoss = 0;

        for(Cuboid wall : walls){
            double thickness = 0;

            for(Layer layer : wall.getLayers()){
                thickness += layer.getThickness();
            }

            overallHeatLoss += thickness * wall.calculateSize() * DENSITY_OF_WALL *
                    SPECIFIC_HEAT_CAPACITY_OF_WALL * ((temperatureInside - wall.getTemperatureOutside())/10);

        }

        for(IrregularCuboid wall : wallsWithWindows){
            double thickness = 0;

            for(Layer layer : wall.getLayers()){
                thickness += layer.getThickness();
            }

            for(Cuboid window : wall.getWindows()){
                double thickness1 = 0;

                for(Layer layer : window.getLayers()){
                    thickness1 += layer.getThickness();
                }

                overallHeatLoss += thickness1 * window.calculateSize() * DENSITY_OF_WALL *
                        SPECIFIC_HEAT_CAPACITY_OF_WALL * ((temperatureInside - window.getTemperatureOutside())/10);
            }



            overallHeatLoss += thickness * wall.calculateSize() * DENSITY_OF_WALL *
                    SPECIFIC_HEAT_CAPACITY_OF_WALL * ((temperatureInside - wall.getTemperatureOutside())/10);

        }

        double thickness = 0;

        for(Layer layer : floor.getLayers()){
            thickness += layer.getThickness();
        }

        overallHeatLoss += thickness * floor.calculateSize() * DENSITY_OF_WALL *
                SPECIFIC_HEAT_CAPACITY_OF_WALL * (temperatureInside - (floor.getTemperatureOutside())/10);

        return overallHeatLoss;
    }

}
