/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Observer;
import map.City;
import map.Map;
import order.Shipment;

/**
 *
 * @author Piotrek
 */
public class Car extends Thread {

    private static int counter = 0;
    private final int id = counter++;
    private final int capacity;
    private int numberOfShipments;
    private HashMap<Integer, Shipment> shipments;
    private ArrayList<Integer> order;
    private int position;
    private boolean isOnWay;
    private Observer observer;
    private Map map;

    public Car(int c) {
        super();
        capacity = c;
        numberOfShipments = 0;
        shipments = new HashMap<>();
        order = new ArrayList<>();
        isOnWay = false;
    }

    public void addObserver(Observer o) {
        observer = o;
    }

    public void addMap(Map m) {
        map = m;
    }

    @Override
    public void run() {
        isOnWay = true;
        observer.startCall(this);
        int actualDestiny;
        int delay;

        while (!order.isEmpty()) {
            actualDestiny = order.remove(order.size()-1);
            delay = map.getConnection(position, actualDestiny);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-3);
            }
            position = actualDestiny;
            observer.callReachedDestination(this);
            Shipment tmp = removeShipment(position);
            if (tmp != null) {
                observer.callRemovedShipment(this, tmp);
            }
        }

        isOnWay = false;
    }

    public int position() {
        return position;
    }

    public int getCarId() {
        return id;
    }

    public synchronized boolean isAvailable() {
        return !isOnWay;
    }

    public void addShipment(Shipment s) {
        if (numberOfShipments + 1 <= capacity) {
            shipments.put(s.whereTo(), s);
            numberOfShipments++;
        }
        if (numberOfShipments == capacity) {
            this.start();
        }
    }

    public Shipment getShipment(int i) {
        return shipments.get(i);
    }

    public int getCapacity() {
        return capacity;
    }

    public Shipment removeShipment(int i) {
        numberOfShipments--;
        return shipments.remove(i);
    }

    public boolean isEmpty() {
        return shipments.isEmpty();
    }

    public boolean isOnPath(int city) {
        return order.contains(city);
    }

    public void addPath(ArrayList<Integer> path) {
        order = path;
    }
    
    public City positionCity() {
        return map.getCityById(position);
    }
}
