package org.sergedb.oop.abstraction.utils;

import java.util.HashMap;
import java.util.Map;

import org.sergedb.oop.abstraction.models.Car;

public class StatsTracker {
    
    private int electric = 0;
    private int gas = 0;
    private int people = 0;
    private int robots = 0;
    private int dining = 0;
    private int notDining = 0;
    private final Map<String, Integer> consumption = new HashMap<>();
    private final Map<String, Integer> stationCustomers = new HashMap<>();

    public StatsTracker() {
        consumption.put("ELECTRIC", 0);
        consumption.put("GAS", 0);
    }

    public synchronized void record(Car car, String stationName) {
        incrementType(car.type());
        incrementPassengers(car.passengers());
        incrementDining(car.isDining());
        addConsumption(car.type(), car.consumption());
        addStationCustomer(stationName);
    }


    public synchronized void incrementType(String type) {
        if ("ELECTRIC".equals(type)) electric++;
        if ("GAS".equals(type)) gas++;
    }

    public synchronized void incrementPassengers(String passengers) {
        if ("PEOPLE".equals(passengers)) people++;
        if ("ROBOTS".equals(passengers)) robots++;
    }

    public synchronized void incrementDining(boolean isDining) {
        if (isDining) dining++;
        else notDining++;
    }

    public synchronized void addConsumption(String type, int amount) {
        consumption.put(type, consumption.getOrDefault(type, 0) + amount);
    }

    public synchronized void addStationCustomer(String stationName) {
        stationCustomers.put(stationName, stationCustomers.getOrDefault(stationName, 0) + 1);
    }

    public int getElectric() { return electric; }
    public int getGas() { return gas; }
    public int getPeople() { return people; }
    public int getRobots() { return robots; }
    public int getDining() { return dining; }
    public int getNotDining() { return notDining; }
    public Map<String, Integer> getConsumption() { return consumption; }

    public synchronized void clear() {
        electric = 0;
        gas = 0;
        people = 0;
        robots = 0;
        dining = 0;
        notDining = 0;
        consumption.clear();
        consumption.put("ELECTRIC", 0);
        consumption.put("GAS", 0);
        stationCustomers.clear();
    }

    @Override
    public String toString() {
        return String.format(
            "\n --- [ STATS SUMMARY ] --- \n - ELECTRIC: %d\n - GAS: %d\n - PEOPLE: %d\n - ROBOTS: %d\n - DINING: %d\n - NOT DINING: %d\n - CONSUMPTION: %s\n - PER STATION: %s\n",
            electric, gas, people, robots, dining, notDining, consumption, stationCustomers
        );
    }
}
