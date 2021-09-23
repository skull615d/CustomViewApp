package cars;

import cars.car_parts.beepers.Beeper;
import cars.car_parts.engines.Engine;
import cars.car_parts.suspension.Suspension;
import cars.car_parts.transmissions.Transmission;
import cars.car_parts.wheels.Wheels;
import cars.car_parts.wipers.Wipers;

public class BMW extends Car {
    public BMW(Beeper beeper, Engine engine, Transmission transmission, Wheels wheels, Suspension suspension, Wipers wipers) {
        super(beeper, engine, transmission, wheels, suspension, wipers);
    }

    public String turnOnTurnSignals() {
        return "Nothing happens...";
    }

}
