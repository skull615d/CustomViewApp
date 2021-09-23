package cars.car_parts.engines;

public class OppositeEngine implements Engine {
    @Override
    public String startEngine() {
        return "Brbrbrbrbr";
    }

    @Override
    public String accelerate() {
        return "brrrrrrrrrr";
    }

    @Override
    public String decelerate() {
        return "bBbBbBbBbB";
    }

    @Override
    public String stopEngine() {
        return "Engine stopped";
    }
}
