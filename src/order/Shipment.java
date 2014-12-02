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

    public Shipment(int id, int whereFrom, int whereTo, String name, int priority) {
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
        if (priority > o.getPriority()) {
            return 1;
        } else if (priority == o.getPriority()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return id + " " + whereFrom + " " + whereTo + " " + name + " " + priority;
    }
}
