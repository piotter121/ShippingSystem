/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import map.City;

/**
 *
 * @author Piotrek
 */
public class Shipment implements Comparable<Shipment> {
    private String name;
    private City whereTo;
    private int priority;
    
    public Shipment(String name, City whereTo, int priority) {
        this.name = name;
        this.whereTo = whereTo;
        this.priority = priority;
    }
    
    public String getName() {
        return name;
    }
    
    public City whereTo() {
        return whereTo;
    }
    
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Shipment o) {
        return this.priority - o.priority;
    }
}
