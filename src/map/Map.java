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
}
