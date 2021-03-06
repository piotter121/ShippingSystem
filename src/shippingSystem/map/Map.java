/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.map;

import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Piotrek
 */
public class Map extends SparseMultigraph<City, Integer> {

//    private Matrix<Integer> connections;
    private ArrayList<City> cities;
    private int base;

    public Map(City... c) {
        super();
        cities = new ArrayList<>(Arrays.asList(c));
//        connections = new Matrix<>(cities.size(), cities.size());
//        initDiag();
        for (City a : c) {
            addVertex(a);
        }
        
    }

    public Map() {
        super();
//        connections = new Matrix<>(0, 0);
        cities = new ArrayList<>();
    }

    public void setBase(int i) {
        base = i;
    }

    public int getBase() {
        return base;
    }

//    private void initDiag() {
//        for (int i = 0; i < cities.size(); i++) {
//            setConnection(cities.get(i).getId(), cities.get(i).getId(), new Integer(0));
//        }
//    }

    public void setConnection(int a, int b, int value) {
        City i = getCityById(a);
        City j = getCityById(b);
        if (i != null && j != null) {
//            connections.set(cities.indexOf(i), cities.indexOf(j), value);
//            connections.set(cities.indexOf(j), cities.indexOf(i), value);
            if (i != j) {
                addEdge(value, i, j);
            }
        } else {
            System.err.println("Nie znaleziono połączenia między miastem nr " + a
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
            Integer o = findEdge(i, j);
            if (o == null) {
                return -1;
            } else {
                return o;
            }
        } else {
            return -1;
        }

    }

    public void addCity(City c) {
        cities.add(c);
//        Matrix<Integer> newConnections = new Matrix<>(cities.size(), cities.size());
//        for (int i = 0; i < newConnections.getRows() - 1; i++) {
//            for (int j = 0; j < newConnections.getCols() - 1; j++) {
//                newConnections.set(i, j, connections.get(i, j));
//            }
//        }
//        connections = newConnections;
//        initDiag();
        addVertex(c);
    }

    @Override
    public String toString() {
        String result = new String();
        for (City a : cities) {
            result += a.toString() + "\n";
        }

//        result += connections.toString();

        return result;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

}
