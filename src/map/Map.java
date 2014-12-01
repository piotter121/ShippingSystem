/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import dataStructures.Matrix;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Piotrek
 */
public class Map {

    Matrix<Integer> connections;
    ArrayList<City> cities;

    public Map(City... c) {
        cities = new ArrayList<>(Arrays.asList(c));
        connections = new Matrix<>(cities.size(), cities.size());
    }

    public void setConnection(City a, City b, int value) {
        int i = cities.indexOf(a);
        int j = cities.indexOf(b);
        connections.set(i, j, value);
        connections.set(j, i, value);
    }

    public int getConnection(City a, City b) {
        int i = cities.indexOf(a);
        int j = cities.indexOf(b);
        return connections.get(i, j);
    }
    
    public void addCity(City c) {
        cities.add(c);
        Matrix<Integer> newConnections = new Matrix<>(cities.size(), cities.size());
        for (int i = 0; i < newConnections.getRows(); i++) {
            for (int j = 0; j < newConnections.getCols(); j++) {
                newConnections.set(i, j, connections.get(i, j));
            }
        }
        connections = newConnections;
    }
}
