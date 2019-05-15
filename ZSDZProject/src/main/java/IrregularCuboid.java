import java.util.List;

/*walls with windows*/
class IrregularCuboid extends Cuboid {
    private final List<Cuboid> windows;

    IrregularCuboid(double temperatureInside, double temperatureOutside, List<Layer> layers, double windVelocity,
                    double height, double width, List<Cuboid> windows){

        super(temperatureInside, temperatureOutside, layers, windVelocity, height, width);
        this.windows = windows;

    }

    public List<Cuboid> getWindows() {
        return windows;
    }

    double calculateSize() {
        double size = height * width;
        for(Cuboid cuboid : windows){
            size -= cuboid.calculateSize();
        }
        return size;
    }

}
