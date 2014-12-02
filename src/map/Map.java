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

    private Matrix<Integer> connections;
    private ArrayList<City> cities;
    private int base;

    public Map(City... c) {
        cities = new ArrayList<>(Arrays.asList(c));
        connections = new Matrix<>(cities.size(), cities.size());
        base = 0;
        initDiag();
    }

    public Map() {
        connections = new Matrix<>(0, 0);
        cities = new ArrayList<>();
        base = 0;
        initDiag();
    }

    private void initDiag() {
        for (int i = 0; i < cities.size(); i++) {
            setConnection(i, i, 0);
        }
    }

    public void setConnection(int a, int b, int value) {
        City i = getCityById(a);
        City j = getCityById(b);
        if (i != null && j != null) {
            connections.set(cities.indexOf(i), cities.indexOf(j), value);
            connections.set(cities.indexOf(j), cities.indexOf(i), value);
        } else {
            System.out.println("Nie znaleziono połączenia między miastem nr " + a
                    + " a miastem nr " + b);
        }
    }

    public City getCityById(int i) {
        for (City c : cities) {
            if (c.getId() == i) {
                return c;
            }
        }
        return null;
    }

    public int getConnection(int a, int b) {
        City i = getCityById(a);
        City j = getCityById(b);
        if (i != null && j != null) {
            return connections.get(cities.indexOf(i), cities.indexOf(j));
        } else {
            return -1;
        }
    }

    public void addCity(City c) {
        cities.add(c);
        Matrix<Integer> newConnections = new Matrix<>(cities.size(), cities.size());
        for (int i = 0; i < newConnections.getRows() - 1; i++) {
            for (int j = 0; j < newConnections.getCols() - 1; j++) {
                newConnections.set(i, j, connections.get(i, j));
            }
        }
        connections = newConnections;
    }

    public void setBase(int n) {
        base = n;
    }

    public int getBase() {
        return base;
    }

    @Override
    public String toString() {
        String result = new String();
        for (City a : cities) {
            result += a.toString() + "\n";
        }

        result += connections.toString();

        return result;
    }
}
