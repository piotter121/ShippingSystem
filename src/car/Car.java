/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.util.ArrayList;
import java.util.HashMap;
import map.City;
import order.Shipment;

/**
 *
 * @author Piotrek
 */
public class Car extends Thread {

    private int capacity;
    private int numberOfShipments;
    private HashMap<Integer, Shipment> shipments;
    private ArrayList<Integer> order;
    private boolean isOnWay;

    public Car(int c) {
        super();
        capacity = c;
        numberOfShipments = 0;
        shipments = new HashMap<>();
        order = new ArrayList<>();
        isOnWay = false;
    }

    @Override
    public void run() {
        isOnWay = true;
    }

    public boolean isFull() {
        return (numberOfShipments == capacity);
    }

    public void addShipment(Shipment s) {
        if (numberOfShipments + 1 <= capacity) {
            shipments.put(s.whereTo(), s);
            numberOfShipments++;
        }
    }

    public Shipment getShipment(int i) {
        if (i < capacity) {
            return shipments.get(i);
        } else {
            return null;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void removeShipment(int i) {
        if (i < capacity) {
            shipments.remove(i);
            numberOfShipments--;
        }
    }

    public boolean isEmpty() {
        return shipments.isEmpty();
    }

    public boolean isOnWay(int city) {
        return order.contains(city);
    }
    
    public void addPath(ArrayList<Integer> path) {
        order.addAll(path);
    }
}
