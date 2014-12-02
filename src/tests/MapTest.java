/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import map.City;
import map.Map;

/**
 *
 * @author Piotrek
 */
public class MapTest {
    public static void main(String[] args) {
        Map m = new Map();
        m.addCity(new City(0, "Zaręby"));
        m.addCity(new City(1, "Zamion"));
        m.setConnection(1, 0, 30);
        System.out.println(m.getConnection(1, 0) == 30);
        m.setBase(0);
        System.out.println("Baza to " + m.getCityById(m.getBase()));
    }
}
