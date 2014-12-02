/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import map.City;

/**
 *
 * @author Piotrek
 */
public class CityTest {
    public static void main(String[] args) {
        City a = new City(0, "Warszawa");
        City b = new City(2, "Olsztyn");
        System.out.println(b.getId() == 0);
        System.out.println(a.getName().equals("Warszawa"));
        System.out.println(b.equals(b));
        System.out.println(a.toString() + " " + b.toString());
    }
}
