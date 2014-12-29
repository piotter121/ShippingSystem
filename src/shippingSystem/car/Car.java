/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import shippingSystem.inputOutput.Observer;
import shippingSystem.map.City;
import shippingSystem.map.Map;
import shippingSystem.shipments.Shipment;

/**
 *
 * @author Piotrek
 */
public class Car extends Thread {

    private static int counter = 0;
    private final int id = counter++;
    private final int capacity;
    private int numberOfShipments;
    private HashMap<Integer, ArrayList<Shipment>> shipments;
    private ArrayList<Integer> path;
    private int position;
    private Observer observer;
    private Map map;

    public Car(int c) {
        super();
        capacity = c;
        numberOfShipments = 0;
        shipments = new HashMap<>();
        path = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        observer = o;
    }

    public void addMap(Map m) {
        map = m;
    }

    @Override
    public void run() {
        observer.startCall(this);
        int actualDestiny;
        int delay;

        while (!path.isEmpty()) {
            actualDestiny = path.remove(path.size() - 1);
            delay = map.getConnection(position, actualDestiny);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-3);
            }
            position = actualDestiny;
            observer.callReachedDestination(this);
            ArrayList<Shipment> tmp = removeShipment(position);
            if (!tmp.isEmpty()) {
                for (Shipment e : tmp) {
                    observer.callRemovedShipment(this, e);
                }
            }
        }
    }

    public int position() {
        return position;
    }

    public int getCarId() {
        return id;
    }

    public boolean isAvailable() {
        return !isAlive();
    }

    public synchronized void addShipment(Shipment s) {
        if (numberOfShipments + 1 <= capacity) {
            ArrayList<Shipment> tmp = shipments.get(s.whereTo());
            if (tmp == null) {
                tmp = new ArrayList<>();
            }
            tmp.add(s);
            shipments.put(s.whereTo(), tmp);
            numberOfShipments++;
        }
        if (numberOfShipments == capacity) {
            this.start();
        }
    }

    public boolean hasShipment() {
        return numberOfShipments > 0;
    }

    public ArrayList<Shipment> getShipments(int i) {
        return shipments.get(i);
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Shipment> removeShipment(int i) {
        numberOfShipments--;
        return shipments.remove(i);
    }

    public boolean isEmpty() {
        return shipments.isEmpty();
    }

    public boolean isOnPath(int city) {
        return path.contains(city);
    }

    public void addPath(ArrayList<Integer> path) {
        this.path = path;
    }

    public City positionCity() {
        return map.getCityById(position);
    }

    @Override
    public String toString() {
        String result = new String();
        result += id + " ";
        result += shipments.toString() + "\n";
        return result;
    }
}
