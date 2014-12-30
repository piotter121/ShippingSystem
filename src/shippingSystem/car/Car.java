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
import shippingSystem.inputOutput.Controler;
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
    private Controler controller;
    private Map map;
    private int time;

    public Car(int c) {
        super();
        time = 0;
        capacity = c;
        numberOfShipments = 0;
        shipments = new HashMap<>();
        path = new ArrayList<>();
    }

    public void addObserver(Controler o) {
        controller = o;
    }

    public void addMap(Map m) {
        map = m;
    }

    @Override
    public void run() {
        controller.startCall(this);
        int actualDestiny;
        int delay;

        while (!path.isEmpty()) {
            actualDestiny = nextPosition();
            delay = map.getConnection(position, actualDestiny);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-3);
            }
            position = actualDestiny;
            controller.callReachedDestination(this);
            ArrayList<Shipment> tmp = removeShipment(position);
            if (tmp != null && !tmp.isEmpty()) {
                for (Shipment e : tmp) {
                    controller.callRemovedShipment(this, e);
                }
            }
        }
        time = 0;
        position = map.getBase();
        controller.callFinishedTrace(this);
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
        controller.callAddedShipment(this, s);
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

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        String result = new String();
        result += id + " ";
        result += shipments.toString() + "\n";
        return result;
    }

    private int nextPosition() {
        int actualDestiny = path.remove(path.size() - 1);
        time += map.getConnection(position, actualDestiny);
        return actualDestiny;
    }

}
