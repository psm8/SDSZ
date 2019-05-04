class Heater {
    private final int power;
    private final int volume;

    Heater(int power, int volume){
        this.power = power;
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public int getVolume() {
        return volume;
    }
}
