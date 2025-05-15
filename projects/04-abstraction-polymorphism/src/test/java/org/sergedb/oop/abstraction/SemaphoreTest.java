package org.sergedb.oop.abstraction;

import org.junit.Before;
import org.junit.Test;
import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.SimpleQueue;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.services.*;
import org.sergedb.oop.abstraction.station.CarStation;
import org.sergedb.oop.abstraction.dispatcher.Semaphore;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SemaphoreTest {

    private final List<CarStation> stations = new ArrayList<>();

    @Before
    public void setupStations() {
        stations.clear();

        createStation("GAS", "PEOPLE");
        createStation("GAS", "ROBOTS");
        createStation("ELECTRIC", "PEOPLE");
        createStation("ELECTRIC", "ROBOTS");
    }

    private void createStation(String fuel, String passengers) {
        Queue<Car> queue = new SimpleQueue<>();

        Dineable dine = passengers.equals("PEOPLE") ? new PeopleDinner() : new RobotDinner();
        Refuelable refuel = fuel.equals("GAS") ? new GasStation() : new ElectricStation();


        stations.add(new CarStation(queue, dine, refuel));
    }

    @Test
    public void testDispatchAndServe() {
        Semaphore dispatcher = new Semaphore(stations);

        // Arrange: 4 cars
        List<Car> cars = List.of(
                new Car("1", "GAS", "PEOPLE", true, 40),
                new Car("2", "ELECTRIC", "ROBOTS", true, 25),
                new Car("3", "GAS", "ROBOTS", false, 30),
                new Car("4", "ELECTRIC", "PEOPLE", false, 35)
        );

        for (Car car : cars) dispatcher.addCar(car);

        dispatcher.serveAll();
    }
}
