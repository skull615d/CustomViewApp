package cars;

import cars.car_parts.beepers.Beeper;
import cars.car_parts.engines.Engine;
import cars.car_parts.suspension.Suspension;
import cars.car_parts.transmissions.Transmission;
import cars.car_parts.wheels.Wheels;
import cars.car_parts.wipers.Wipers;

public class Subaru extends Car {

    public Subaru(Beeper beeper, Engine engine, Transmission transmission, Wheels wheels, Suspension suspension, Wipers wipers) {
        super(beeper, engine, transmission, wheels, suspension, wipers);
    }

    @Override
    public String honk() {
        System.out.println("Subaru overrides honk()");
        return "Bip Bip Bip";
    }

    public String drift() {
        return "Drifting";
    }

}
