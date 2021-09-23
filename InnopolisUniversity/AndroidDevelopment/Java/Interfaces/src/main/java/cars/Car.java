package cars;

import cars.car_parts.beepers.Beeper;
import cars.car_parts.engines.Engine;
import cars.car_parts.suspension.Suspension;
import cars.car_parts.transmissions.Transmission;
import cars.car_parts.wheels.Wheels;
import cars.car_parts.wipers.Wipers;

public abstract class Car implements Beeper, Engine, Suspension, Transmission, Wheels, Wipers {

    private final Beeper beeper;
    private final Engine engine;
    private final Transmission transmission;
    private final Wheels wheels;
    private final Suspension suspension;
    private final Wipers wipers;

    public Car(Beeper beeper, Engine engine, Transmission transmission, Wheels wheels, Suspension suspension, Wipers wipers) {
        this.beeper = beeper;
        this.engine = engine;
        this.transmission = transmission;
        this.wheels = wheels;
        this.suspension = suspension;
        this.wipers = wipers;
    }

    @Override
    public String honk() {
        return beeper.honk();
    }

    @Override
    public String startEngine() {
        return engine.startEngine();
    }

    @Override
    public String accelerate() {
        return engine.accelerate();
    }

    @Override
    public String decelerate() {
        return engine.decelerate();
    }

    @Override
    public String stopEngine() {
        return engine.stopEngine();
    }

    @Override
    public String switchGear() {
        return transmission.switchGear();
    }

    @Override
    public String move() {
        return wheels.move();
    }

    @Override
    public String doSuspensionThing() {
        return suspension.doSuspensionThing();
    }

    @Override
    public String cleanWindshield() {
        return wipers.cleanWindshield();
    }

}
