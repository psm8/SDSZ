import java.util.List;

/*windows, floor*/
public class Cuboid {
    private double temperatureInside;
    private double temperatureOutside;
    private final List<Layer> layers;
    private final double insideConvectionCoeff;
    private final double outsideConvectionCoeff;
    final double height;
    final double width;

    Cuboid(double temperatureInside, double temperatureOutside, List<Layer> layers, double insideConvectionCoeff,
           double outsideConvectionCoeff, double height, double width){

        this.temperatureInside = temperatureInside;
        this.temperatureOutside = temperatureOutside;
        this.layers = layers;
        this.insideConvectionCoeff = insideConvectionCoeff;
        this.outsideConvectionCoeff = outsideConvectionCoeff;
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

    double calculateOverallHeatTransferCoeff(){

        /* 1 / U = 1 / hci + Σ (sn / kn) + 1 / hco
           U = the overall heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           kn = thermal conductivity of material in layer n  (W/(m K), Btu/(hr ft °F))
           hc i,o = inside or outside wall individual fluid convection heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           sn = thickness of layer n (m, ft) */

        double overallHeatTransferCoeffInversed = 1/insideConvectionCoeff + 1/outsideConvectionCoeff;
        for(Layer layer : layers){
            overallHeatTransferCoeffInversed += layer.getThickness()/layer.getConductivity();
        }

        return 1/overallHeatTransferCoeffInversed;
    }

    double calculatHeatTransfer() {

        /* q = U A dT
           q = heat transfer (W (J/s), Btu/h)
           U = overall heat transfer coefficient (W/(m2K), Btu/(ft2 h oF))
           A = wall area (m2, ft2)
           dT = (t1 - t2) = temperature difference over wall (oC, oF) */

        return calculateOverallHeatTransferCoeff() * calculateSize() * (temperatureOutside - temperatureInside);
    }

    double calculateSize() {
        return height * width;
    }

}
