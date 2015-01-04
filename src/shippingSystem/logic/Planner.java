/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.logic;

import shippingSystem.car.Car;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import shippingSystem.map.City;
import shippingSystem.map.Map;
import shippingSystem.shipments.Shipment;
import shippingSystem.shipments.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Planner {

    private static final int INFINITY = Integer.MAX_VALUE - 1000;

    private HashMap<Integer, Integer> d, p;
    private HashMap<Integer, City> q, s;
    private Set<Integer> keys;
    private Map m;
    
    public Planner(Map m) {
        this.m = m;
    }

    public void calculatePaths() {
        q = new HashMap<>();
        ArrayList<City> tmp = new ArrayList<>(m.getCities());
        for (City temp : tmp) {
            q.put(temp.getId(), temp);
        }
        s = new HashMap<>();
        int current, w;

        d = new HashMap<>();
        p = new HashMap<>();
        keys = new LinkedHashSet<>(q.keySet());

        initialize();

        while (!q.isEmpty()) {
            current = getMin();
            removeAndAdd(current);

            ArrayList<City> n = getNeighbours(current, m);
            for (City a : n) {
                w = a.getId();
                if (d.get(w) > d.get(current) + m.getConnection(w, current)) {
                    d.put(w, d.get(current) + m.getConnection(w, current));
                    p.put(w, current);
                }
            }
        }
    }

    public void pickShipmentsToCars(Car[] cars, ShipmentsList ordersQueue) {
        Shipment actual;
        Car car;
        while (checkCarsIfAvailable(cars) && !ordersQueue.isEmpty()) {
            actual = ordersQueue.getFromTop();
            if ((car = checkOnWays(cars, actual.whereTo())) != null) {
                car.addShipment(actual);
            } else {
                if (checkCarsIfAnyEmpty(cars)) {
                    car = getEmpty(cars);
                    car.addPath(calculateTo(actual.whereTo()));
                    car.addShipment(actual);
                } else {
                    ordersQueue.add(actual);
                    return;
                }
            }
        }
    }

    private ArrayList<Integer> calculateTo(int i) {
        ArrayList<Integer> path = new ArrayList<>();
        while (i != m.getBase()) {
            if (i > -1) {
                path.add(i);
                i = p.get(i);
            }
        }
        return path;
    }

    private boolean checkCarsIfAnyEmpty(Car[] cars) {
        boolean isAnyEmpty = false;
        for (Car e : cars) {
            if (e.isEmpty() && e.isAvailable()) {
                isAnyEmpty = true;
                break;
            }
        }
        return isAnyEmpty;
    }

    public boolean checkCarsIfAvailable(Car[] cars) {
        boolean ifAnyAvailable = false;
        for (Car e : cars) {
            if (e.isAvailable()) {
                ifAnyAvailable = true;
            }
        }
        return ifAnyAvailable;
    }

    private int getMin() {
        int minVal = INFINITY;
        int minKey = 0;
        Set<Integer> k = q.keySet();
        for (int a : k) {
            if (d.get(a) < minVal || k.size() == 1) {
                minVal = d.get(a);
                minKey = a;
            }
        }

        return minKey;
    }

    private void removeAndAdd(int u) {
        City tmp = q.remove(u);
        s.put(tmp.getId(), tmp);
    }

    private void initialize() {
        for (int key : keys) {
            if (key == m.getBase()) {
                d.put(key, 0);
                p.put(key, -1);
            } else {
                d.put(key, INFINITY);
                p.put(key, -1);
            }
        }
    }

    private ArrayList<City> getNeighbours(int id, Map m) {
        ArrayList<City> n = new ArrayList<>();
        Set<Integer> available = q.keySet();
        City tmp;
        for (int a : available) {
            tmp = m.getCityById(a);
            if (m.getConnection(a, id) > 0) {
                n.add(tmp);
            }
        }

        return n;
    }

    private Car checkOnWays(Car[] cars, int city) {
        for (Car e : cars) {
            if (e.isOnPath(city) && e.isAvailable()) {
                return e;
            }
        }
        return null;
    }

    private Car getEmpty(Car[] cars) {
        for (Car e : cars) {
            if (e.isEmpty() && e.isAvailable()) {
                return e;
            }
        }
        return null;
    }

}
