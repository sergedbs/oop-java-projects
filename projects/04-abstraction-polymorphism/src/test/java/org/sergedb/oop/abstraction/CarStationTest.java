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

    private AbstractDineable dinner;
    private AbstractRefuelable refuel;

    @Before
    public void setUp() {
        dinner = new PeopleDinner();
        refuel = new GasStation();
    }

    @Test
    public void testServeCars() {

        Queue<Car> queue = new SimpleQueue<>();

        Car c1 = new Car("1", "GAS", "PEOPLE", true, 40);
        Car c2 = new Car("2", "GAS", "PEOPLE", false, 30);
        Car c3 = new Car("3", "GAS", "PEOPLE", true, 20);

        queue.enqueue(c1);
        queue.enqueue(c2);
        queue.enqueue(c3);

        CarStation station = new CarStation(queue, dinner, refuel);

        station.serveCars();

        assertEquals(List.of("1", "3"), dinner.getServedCars());
        assertEquals(2, dinner.getServedCount());

        assertEquals(List.of("1", "2", "3"), refuel.getRefueledCars());
        assertEquals(3, refuel.getRefueledCount());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testServeEmptyQueue() {
        Queue<Car> queue = new SimpleQueue<>();
        CarStation station = new CarStation(queue, dinner, refuel);

        station.serveCars();

        assertTrue(dinner.getServedCars().isEmpty());
        assertEquals(0, dinner.getServedCount());

        assertTrue(refuel.getRefueledCars().isEmpty());
        assertEquals(0, refuel.getRefueledCount());
    }

    @Test
    public void testNoDiningCalled() {
        Queue<Car> queue = new SimpleQueue<>();
        queue.enqueue(new Car("4", "GAS", "PEOPLE", false, 15));

        CarStation station = new CarStation(queue, dinner, refuel);
        station.serveCars();

        assertTrue(dinner.getServedCars().isEmpty());
        assertEquals(List.of("4"), refuel.getRefueledCars());
    }

}

