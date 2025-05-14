package org.sergedb.oop.abstraction;

import org.junit.Before;
import org.junit.Test;
import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.queue.SimpleQueue;
import org.sergedb.oop.abstraction.services.*;
import org.sergedb.oop.abstraction.station.CarStation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarStationTest {

    private class MockRefuelable implements Refuelable {
        private final List<String> log = new ArrayList<>();

        @Override
        public void refuel(String carId) {
            log.add(carId);
        }

        public List<String> getLog() {
            return log;
        }
    }

    private class MockDineable implements Dineable {
        private final List<String> log = new ArrayList<>();

        @Override
        public void serveDinner(String carId) {
            log.add(carId);
        }

        public List<String> getLog() {
            return log;
        }
    }

    private MockDineable dinner;
    private MockRefuelable refuel;

    @Before
    public void setUp() {
        dinner = new MockDineable();
        refuel = new MockRefuelable();
    }

    @Test
    public void testServeCars() {

        Queue<Car> queue = new SimpleQueue<>();

        Car c1 = new Car("1", "GAS", "PEOPLE", true, 40);
        Car c2 = new Car("2", "ELECTRIC", "ROBOTS", false, 30);
        Car c3 = new Car("3", "ELECTRIC", "PEOPLE", true, 20);

        queue.enqueue(c1);
        queue.enqueue(c2);
        queue.enqueue(c3);

        CarStation station = new CarStation(queue, dinner, refuel);

        station.serveCars();

        assertEquals(List.of("1", "3"), dinner.getLog());
        assertEquals(List.of("1", "2", "3"), refuel.getLog());
        assertTrue(queue.isEmpty());
    }
}

