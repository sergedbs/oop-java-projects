package org.sergedb.oop.abstraction;

import org.junit.Before;
import org.junit.Test;
import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.SimpleQueue;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.services.*;
import org.sergedb.oop.abstraction.station.CarStation;
import org.sergedb.oop.abstraction.dispatcher.Semaphore;
import org.sergedb.oop.abstraction.utils.LogBuffer;
import org.sergedb.oop.abstraction.utils.StatsTracker;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SemaphoreTest {

    private final List<CarStation> stations = new ArrayList<>();
    private final List<AbstractRefuelable> refuelServices = new ArrayList<>();
    private final List<AbstractDineable> dineServices = new ArrayList<>();

    private final LogBuffer logBuffer = new LogBuffer();
    private StatsTracker statsTracker;

    @Before
    public void setupStations() {
        stations.clear();

        createStation(new GasStation(), new PeopleDinner());
        createStation(new GasStation(), new RobotDinner());
        createStation(new ElectricStation(), new PeopleDinner());
        createStation(new ElectricStation(), new RobotDinner());
    }

    private void createStation(AbstractRefuelable refuel, AbstractDineable dine) {
        Queue<Car> queue = new SimpleQueue<>();
        statsTracker = new StatsTracker();

        refuelServices.add(refuel);
        dineServices.add(dine);
        stations.add(new CarStation("Test", queue, dine, refuel, statsTracker));
    }

    @Test
    public void testDispatchAndServe() {
        Semaphore dispatcher = new Semaphore(stations);

        List<Car> cars = List.of(
                new Car("1", "GAS", "PEOPLE", true, 40),     // station 0
                new Car("2", "GAS", "ROBOTS", false, 30),    // station 1
                new Car("3", "ELECTRIC", "PEOPLE", false, 20), // station 2
                new Car("4", "ELECTRIC", "ROBOTS", true, 35)   // station 3
        );

        for (Car car : cars) dispatcher.addCar(car);

        dispatcher.serveAll(logBuffer);
        logBuffer.flush(1);

        // Assert per station:
        // Station 0: GAS + PEOPLE
        assertEquals(List.of("1"), refuelServices.get(0).getRefueledCars());
        assertEquals(List.of("1"), dineServices.get(0).getServedCars());

        // Station 1: GAS + ROBOTS
        assertEquals(List.of("2"), refuelServices.get(1).getRefueledCars());
        assertEquals(List.of(), dineServices.get(1).getServedCars());

        // Station 2: ELECTRIC + PEOPLE
        assertEquals(List.of("3"), refuelServices.get(2).getRefueledCars());
        assertEquals(List.of(), dineServices.get(2).getServedCars());

        // Station 3: ELECTRIC + ROBOTS
        assertEquals(List.of("4"), refuelServices.get(3).getRefueledCars());
        assertEquals(List.of("4"), dineServices.get(3).getServedCars());
    }
}
