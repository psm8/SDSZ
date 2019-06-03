package sample.entities;

import java.util.List;

/*windows, floor*/
public class Cuboid {
    private double temperatureInside;
    private double temperatureOutside;
    private final List<Layer> layers;
    private final double windVelocity;
    final double height;
    final double width;
    double heatTransfer;
    double conductiveHeatTransferCoeff;
    double overallHeatTransferCoeffInversed;

    public Cuboid(double temperatureInside, double temperatureOutside, List<Layer> layers,
                  double windVelocity, double height, double width){

        this.temperatureInside = temperatureInside;
        this.temperatureOutside = temperatureOutside;
        this.layers = layers;
        this.windVelocity = windVelocity;
        this.height = height;
        this.width = width;

    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setTemperatureInside(double temperatureInside) {
        this.temperatureInside = temperatureInside;
    }

    public void setTemperatureOutside(double temperatureOutside) {
        this.temperatureOutside = temperatureOutside;
    }

    double calculateConductiveHeatTransferCoeff(){
        conductiveHeatTransferCoeff = 0;
        for(Layer layer : layers){
            conductiveHeatTransferCoeff += layer.getThickness()/layer.getConductivity();
        }

        return conductiveHeatTransferCoeff;
    }

    double calculateConvectiveHeatTransferCoeff(double windVelocity){

        /*hc = 10.45 - v + 10 v1/2
          v = relative speed between object surface and air (m/s)*/

        double convectiveHeatTransferCoeff = 10.45 - windVelocity + 10 * Math.pow(windVelocity, 1/2);

        return convectiveHeatTransferCoeff;
    }

    double calculateOverallHeatTransferCoeff() {

        /* 1 / U = 1 / hci + Σ (sn / kn) + 1 / hco
           U = the overall heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           kn = thermal conductivity of material in layer n  (W/(m K), Btu/(hr ft °F))
           hc i,o = inside or outside wall individual fluid convection heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           sn = thickness of layer n (m, ft) */

        overallHeatTransferCoeffInversed = 1/calculateConvectiveHeatTransferCoeff(1) + 1/calculateConvectiveHeatTransferCoeff(windVelocity);
        overallHeatTransferCoeffInversed += calculateConductiveHeatTransferCoeff();

        return 1/overallHeatTransferCoeffInversed;
    }

    double calculatHeatTransfer() {

        /* q = U A dT
           q = heat transfer (W (J/s), Btu/h)
           U = overall heat transfer coefficient (W/(m2K), Btu/(ft2 h oF))
           A = wall area (m2, ft2)
           dT = (t1 - t2) = temperature difference over wall (oC, oF) */

        heatTransfer = calculateOverallHeatTransferCoeff() * calculateSize() * (temperatureOutside - temperatureInside);
        return heatTransfer;
    }

    double calculateSize() {
        return height * width;
    }

}
