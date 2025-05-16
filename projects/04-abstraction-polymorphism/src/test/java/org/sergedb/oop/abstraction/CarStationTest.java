package org.sergedb.oop.abstraction;

import org.junit.Before;
import org.junit.Test;
import org.sergedb.oop.abstraction.models.Car;
import org.sergedb.oop.abstraction.queue.Queue;
import org.sergedb.oop.abstraction.queue.SimpleQueue;
import org.sergedb.oop.abstraction.services.*;
import org.sergedb.oop.abstraction.station.CarStation;
import org.sergedb.oop.abstraction.utils.LogBuffer;
import org.sergedb.oop.abstraction.utils.StatsTrack;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarStationTest {

    private AbstractDineable dinner;
    private AbstractRefuelable refuel;
    private LogBuffer logBuffer;
    private StatsTrack statsTrack;

    @Before
    public void setUp() {
        dinner = new PeopleDinner();
        refuel = new GasStation();
        logBuffer = new LogBuffer();
        statsTrack = new StatsTrack();
    }

    @Test
    public void testServeCars() {

        statsTrack.clear();

        Queue<Car> queue = new SimpleQueue<>();

        Car c1 = new Car("1", "GAS", "PEOPLE", true, 40);
        Car c2 = new Car("2", "GAS", "PEOPLE", false, 30);
        Car c3 = new Car("3", "GAS", "PEOPLE", true, 20);

        queue.enqueue(c1);
        queue.enqueue(c2);
        queue.enqueue(c3);

        CarStation station = new CarStation("GP", queue, dinner, refuel, statsTrack);

        station.serveCars(logBuffer);
        logBuffer.flush(1);

        assertEquals(List.of("1", "3"), dinner.getServedCars());
        assertEquals(2, dinner.getServedCount());

        assertEquals(List.of("1", "2", "3"), refuel.getRefueledCars());
        assertEquals(3, refuel.getRefueledCount());

        assertTrue(queue.isEmpty());

        assertEquals(3, statsTrack.getGas());
        assertEquals(0, statsTrack.getElectric());
        assertEquals(3, statsTrack.getPeople());
        assertEquals(0, statsTrack.getRobots());
        assertEquals(2, statsTrack.getDining());
        assertEquals(1, statsTrack.getNotDining());
        assertEquals(90, statsTrack.getConsumption().get("GAS").intValue());
    }

    @Test
    public void testServeEmptyQueue() {
        Queue<Car> queue = new SimpleQueue<>();
        CarStation station = new CarStation("GP", queue, dinner, refuel, statsTrack);

        station.serveCars(logBuffer);
        logBuffer.flush(2);

        assertTrue(dinner.getServedCars().isEmpty());
        assertEquals(0, dinner.getServedCount());

        assertTrue(refuel.getRefueledCars().isEmpty());
        assertEquals(0, refuel.getRefueledCount());
    }

    @Test
    public void testNoDiningCalled() {
        Queue<Car> queue = new SimpleQueue<>();
        queue.enqueue(new Car("4", "GAS", "PEOPLE", false, 15));

        CarStation station = new CarStation("GP", queue, dinner, refuel, statsTrack);
        station.serveCars(logBuffer);
        logBuffer.flush(3);

        assertTrue(dinner.getServedCars().isEmpty());
        assertEquals(List.of("4"), refuel.getRefueledCars());
    }

}

