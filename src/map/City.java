/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

/**
 *
 * @author Piotrek
 */
public class City {
    private final int id;
    private final String name;

    public City(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        City c = (City) o;
        return id == c.getId();
    }
    
    @Override 
    public String toString() {
        return id + " = " + name;
    }
}
