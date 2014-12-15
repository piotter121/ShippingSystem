/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import car.Car;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import map.City;
import map.Map;
import order.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Planner {

    private static final int INFINITY = Integer.MAX_VALUE - 1000;

    private int base = -1;
    private HashMap<Integer, Integer> d, p;
    private HashMap<Integer, City> q, s;
    private Set<Integer> keys;

    public void setBase(int base) {
        this.base = base;
    }

    public void calculatePaths(Map m) {
        q = new HashMap<>();
        ArrayList<City> tmp = new ArrayList<>(m.getCities());
        for (City temp : tmp) {
            q.put(temp.getId(), temp);
        }
        s = new HashMap<>();
        int current, w;

        d = new HashMap<>();
        p = new HashMap<>();
        keys = q.keySet();

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

    void pickShipmentsToCars(Car[] cars, ShipmentsList ordersQueue) {
    }

    private int getMin() {
        int minVal = INFINITY;
        int minKey = 0;
        for (int a : keys) {
            if (d.get(a) < minVal) {
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
            if (key == base) {
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

}
