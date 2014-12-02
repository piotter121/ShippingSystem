/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

/**
 *
 * @author Piotrek
 */
public class Shipment implements Comparable<Shipment> {
    private final int id;
    private String name;
    private int whereFrom;
    private int whereTo;
    private int priority;
    
    public Shipment(int id, String name, int whereFrom, int whereTo, int priority) {
        this.id = id;
        this.name = name;
        this.whereFrom = whereFrom;
        this.whereTo = whereTo;
        this.priority = priority;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int whereTo() {
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
