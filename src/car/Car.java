/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.util.ArrayList;
import order.Shipment;

/**
 *
 * @author Piotrek
 */
public class Car extends Thread {

    private int capacity;
    private int numberOfShipments;
    private ArrayList<Shipment> shipments;

    public Car(int c) {
        super();
        capacity = c;
        numberOfShipments = 0;
        shipments = new ArrayList<>();
    }

    public void addShipment(Shipment s) {
        shipments.add(s);
    }

    public Shipment getShipment(int i) {
        if (i < capacity) {
            return shipments.get(i);
        } else {
            return null;
        }
    }
    
    public void removeShipment(int i) {
        if (i < capacity) {
            shipments.remove(i);
        }
    }
}
