import java.util.List;

/*walls with windows*/
class IrregularCuboid extends Cuboid {
    private final List<Cuboid> cuboids;

    IrregularCuboid(double temperatureInside, double temperatureOutside, List<Layer> layers, double insideConvercionCoeff,
                    double outsideConvectionCoeff, double height, double width, List<Cuboid> cuboids){

        super(temperatureInside, temperatureOutside, layers, insideConvercionCoeff, outsideConvectionCoeff, height, width);
        this.cuboids = cuboids;

    }

    double calculateSize() {
        double size = height * width;
        for(Cuboid cuboid : cuboids){
            size -= cuboid.calculateSize();
        }
        return size;
    }

}
