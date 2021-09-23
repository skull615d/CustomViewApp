package bus;

import cars.Car;
import cars.car_parts.beepers.Beeper;
import cars.car_parts.engines.Engine;
import cars.car_parts.suspension.Suspension;
import cars.car_parts.transmissions.Transmission;
import cars.car_parts.wheels.Wheels;
import cars.car_parts.wipers.Wipers;

public class Bus extends Car implements PublicTransport {


    public Bus(Beeper beeper, Engine engine, Transmission transmission, Wheels wheels, Suspension suspension, Wipers wipers) {
        super(beeper, engine, transmission, wheels, suspension, wipers);
    }

    @Override
    public String transportPeople() {
        return "Transporting people from Bus stop to Bus stop";
    }

    @Override
    public String followRoute() {
        return "Following Bus route";
    }

    @Override
    public String makeStopsOnRoute() {
        return "Stopping on Bus Stop";
    }
}
