package cars.car_parts.engines;

public class BusEngine implements Engine {
    @Override
    public String startEngine() {
        return "TrtrTrtrTrtr";
    }

    @Override
    public String accelerate() {
        return "Bus speeding up";
    }

    @Override
    public String decelerate() {
        return "Bus stopping";
    }

    @Override
    public String stopEngine() {
        return "Engine stopped";
    }
}
