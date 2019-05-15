import java.util.List;

public class Temperature {
    List<Double> temperature;
    int measurementsInterval;

    public Temperature(List<Double> temperature, int measurementsInterval) {
        this.temperature = temperature;
        this.measurementsInterval = measurementsInterval;
    }

    public List<Double> getTemperature() {
        return temperature;
    }

    public int getMeasurementsInterval() {
        return measurementsInterval;
    }
}
