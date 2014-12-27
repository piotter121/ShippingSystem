/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import dataStructures.Heap;
import order.Shipment;

/**
 *
 * @author Piotrek
 */
public class HeapTest {

    public static void main(String[] args) {
        Heap<Shipment> h = new Heap<>();
        h.push(new Shipment(0, 3, 0, "Laptop dla p. Kowalskiego", 0));
        h.push(new Shipment(1, 3, 2, "Robot dla NASA", 0));
        h.push(new Shipment(2, 3, 0, "Paczka indeksów dla PW", 0));
        h.push(new Shipment(3, 3, 1, "Pudełka dla IKEA", 10));
        while (!h.isEmpty()) {
            System.out.println(h.pop());
        }
    }
}
