/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.shipments;

import dataStructures.Heap;

/**
 *
 * @author Piotrek
 */
public class ShipmentsList {

    Heap<Shipment> list;

    public ShipmentsList() {
        list = new Heap<>();
    }

    public void add(Shipment a) {
        list.push(a);
    }

    public Shipment getFromTop() {
        return list.pop();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}
