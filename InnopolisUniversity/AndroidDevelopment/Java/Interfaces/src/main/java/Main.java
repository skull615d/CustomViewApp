import bus.Bus;
import cars.Car;
import cars.Subaru;
import cars.car_parts.beepers.Horn;
import cars.car_parts.engines.BusEngine;
import cars.car_parts.engines.OppositeEngine;
import cars.car_parts.suspension.ReinforcedSuspension;
import cars.car_parts.transmissions.RobotTransmission;
import cars.car_parts.wheels.AlloyWheels;
import cars.car_parts.wipers.BoschWipers;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Horn horn = new Horn();
        OppositeEngine engine = new OppositeEngine();
        RobotTransmission transmission = new RobotTransmission();
        AlloyWheels wheels = new AlloyWheels();
        ReinforcedSuspension suspension = new ReinforcedSuspension();
        BoschWipers wipers = new BoschWipers();

        Subaru subaru = new Subaru(horn, engine, transmission, wheels, suspension, wipers);
        System.out.println(subaru.startEngine());
        System.out.println(subaru.accelerate());
        System.out.println(subaru.drift());
        System.out.println(subaru.decelerate());
        System.out.println(subaru.stopEngine());
        System.out.println(subaru.honk());

        System.out.println("----------------------");

        BusEngine busEngine = new BusEngine();
        Bus bus = new Bus(horn, busEngine, transmission, wheels, suspension, wipers);
        System.out.println(bus.startEngine());
        System.out.println(bus.followRoute());
        System.out.println(bus.makeStopsOnRoute());
        System.out.println(bus.transportPeople());
        System.out.println(bus.makeStopsOnRoute());

        System.out.println("----------------------");

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(subaru);
        cars.add(bus);
        cars.forEach(car -> System.out.println(car.startEngine()));

    }
}
