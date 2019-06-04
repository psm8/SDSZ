package sample.entities;

public class Layer {
    private final double thickness;
    private final double conductivity;

    public Layer(double thickness, double conductivity){
        this.thickness = thickness;
        this.conductivity = conductivity;
    }

    public double getThickness() {
        return thickness;
    }

    public double getConductivity() {
        return conductivity;
    }

}

