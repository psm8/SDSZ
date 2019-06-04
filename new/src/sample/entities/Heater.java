package sample.entities;

public class Heater {
    private final double nominalPower;
    private double maxPower;
    private final double volume;
    /*nominal when inlet water temperature ti = 80oC, outlet water temperature out tr = 60oC and surrounding air temperature ta = 20oC*/
    private final double waterTemperatureInlet;
    private final double waterTemperatureOutlet;
    private final double constantDescribingTheTypeOfRadiator;
    private double surroundingAirTemperature;
    private double power;


    public Heater(double nominalPower, double volume, double waterTemperatureInlet, double waterTemperatureOutlet, double surroundingAirTemperature, double constantDescribingTheTypeOfRadiator) {
        this.nominalPower = nominalPower;
        this.maxPower =  calculatemaxPower();
        this.volume = volume;
        this.waterTemperatureInlet = waterTemperatureInlet;
        this.waterTemperatureOutlet = waterTemperatureOutlet;
        this.surroundingAirTemperature = surroundingAirTemperature;
        this.constantDescribingTheTypeOfRadiator = constantDescribingTheTypeOfRadiator;
        calculateRealPower();
    }

    /*allow to just set constant nominal power for example for human*/
    public Heater(double nominalPower, double volume, double surroundingAirTemperature) {
        this.nominalPower = nominalPower;
        this.maxPower =  calculatemaxPower();
        this.volume = volume;
        this.waterTemperatureInlet = 70;
        this.waterTemperatureOutlet = 50;
        this.surroundingAirTemperature = surroundingAirTemperature;
        this.constantDescribingTheTypeOfRadiator = 1.33;
        calculateRealPower();
    }

    public double getVolume() {
        return volume;
    }

    public double getPower() {
        return power;
    }

    public void setSurroundingAirTemperature(double surroundingAirTemperature) {
        this.surroundingAirTemperature = surroundingAirTemperature;
    }

    void calculateRealPower(){

        /* P = P50 [ (ti - tr) / ln( (ti - ta) / (tr - ta) ) 1 / 49.32 ]^n
           P = heat emission from radiator (W, J/s)
           P50 = heat emission from radiator with temperature difference 50 oC (W)
           ti = water temperature inlet (oC)
           tr = water temperature outlet (oC)
           ta = surrounding air temperature (oC)
           n = constant describing the type of radiator (1.33 for standard panel radiators, 1.3 - 1.6 for convectors)*/

        power = nominalPower * Math.pow((waterTemperatureInlet - waterTemperatureOutlet)/Math.log((waterTemperatureInlet - surroundingAirTemperature)/(waterTemperatureOutlet - surroundingAirTemperature)) * 1/49.32,constantDescribingTheTypeOfRadiator);
    }

    double calculatemaxPower(){

        /* P = P50 [ (ti - tr) / ln( (ti - ta) / (tr - ta) ) 1 / 49.32 ]^n
           P = heat emission from radiator (W, J/s)
           P50 = heat emission from radiator with temperature difference 50 oC (W)
           ti = water temperature inlet (oC)
           tr = water temperature outlet (oC)
           ta = surrounding air temperature (oC)
           n = constant describing the type of radiator (1.33 for standard panel radiators, 1.3 - 1.6 for convectors)*/

        return nominalPower * Math.pow((waterTemperatureInlet - waterTemperatureOutlet)/Math.log((waterTemperatureInlet - surroundingAirTemperature)/(waterTemperatureOutlet - surroundingAirTemperature)) * 1/49.32, constantDescribingTheTypeOfRadiator);
    }

    void smartSetPower(double targetTemperature){
        maxPower =  calculatemaxPower();

        if(targetTemperature < surroundingAirTemperature){
            if(power >= 10) {
                power -= 10;
            }
        } else{
            if(power <= maxPower - 10){
                power += 10;
            }
        }

        if(power > maxPower){
            power = maxPower;
        }
    }
}
