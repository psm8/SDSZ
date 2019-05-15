import java.util.List;

/*for floor with air below ex. apartment in flat just use Cuboid*/
public class FloorOnGround extends Cuboid{
    FloorOnGround(double temperatureInside, double temperatureOutside, List<Layer> layers, double windVelocity,
                  double height, double width){

        super(temperatureInside, temperatureOutside, layers, windVelocity, height, width);

    }

    double calculateOverallHeatTransferCoeff() {

        /* 1 / U = 1 / hci + Σ (sn / kn)
           U = the overall heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           kn = thermal conductivity of material in layer n  (W/(m K), Btu/(hr ft °F))
           hc i,o = inside or outside wall individual fluid convection heat transfer coefficient (W/(m2 K), Btu/(ft2 h oF))
           sn = thickness of layer n (m, ft) */

        overallHeatTransferCoeffInversed = 1/calculateConvectiveHeatTransferCoeff(1);
        overallHeatTransferCoeffInversed += 1/calculateConductiveHeatTransferCoeff();

        return 1/overallHeatTransferCoeffInversed;
    }
}
