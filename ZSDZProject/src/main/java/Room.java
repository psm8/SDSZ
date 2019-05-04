import java.util.List;

public class Room {
    /*specific heat capacity of air ~= 1005 J/(kg * K); accuracy 0.2%*/
    final int SPECIFIC_HEAT_CAPACITY_OF_AIR = 1005;
    /*air pressure  = 101325 Pa*/
    final int AIR_PRESSURE = 101325;
    /*specific gas constant  for dry air = 287.05 J/(kg*K)*/
    final double SPECIFIC_GAS_CONSTANT_OF_DRY_AIR = 287.05;
    double temperatureInside;
    private final List<IrregularCuboid> irregularCuboids;
    private final List<List<Cuboid>> cuboidss;
    private final Cuboid floor;
    /*humans could be also heaters*/
    private final List<Heater> heaters;

    Room(int temperatureInside, List<IrregularCuboid> irregularCuboids, List<List<Cuboid>> cuboidss, Cuboid floor, List<Heater> heaters){
        this.temperatureInside = temperatureInside;
        this.irregularCuboids = irregularCuboids;
        this.cuboidss = cuboidss;
        this.floor = floor;
        this.heaters = heaters;

    }

    public double getTemperatureInside() {
        return temperatureInside;
    }

    void calculateTemperature(){

        /* dT = Q/(m * c)
           T - temperature change (K)
           Q - heat exchanged
           m - mass (kg)
           c - specific heat capacity J/(kg * K)*/

        /*depends on temperature inside, so must be calculated before*/
        double airDensity = calculateAirDensity();

        for(Heater heater : heaters) {
            temperatureInside += heater.getPower()/
                    (calculateVolume() * airDensity  * SPECIFIC_HEAT_CAPACITY_OF_AIR);
        }

        for(IrregularCuboid irregularCuboid : irregularCuboids) {
            temperatureInside += irregularCuboid.calculatHeatTransfer()/
                    (calculateVolume() * airDensity  * SPECIFIC_HEAT_CAPACITY_OF_AIR);
        }


        for(List<Cuboid> cuboids : cuboidss) {
            for(Cuboid cuboid : cuboids) {
                temperatureInside += cuboid.calculatHeatTransfer() /
                        (calculateVolume() * airDensity * SPECIFIC_HEAT_CAPACITY_OF_AIR);
            }
        }

        for(IrregularCuboid irregularCuboid : irregularCuboids) {
            irregularCuboid.setTemperatureInside(temperatureInside);
        }

        for(List<Cuboid> cuboids : cuboidss) {
            for (Cuboid cuboid : cuboids) {
                cuboid.setTemperatureInside(temperatureInside);
            }
        }
    }

    private double calculateVolume(){
        /*room is rectangle*/
        double base = floor.getHeight() * floor.getWidth();
        /*all windows have same height*/
        double volume = base * irregularCuboids.get(0).getHeight();

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

}
