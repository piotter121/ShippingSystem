/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.map;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Piotrek
 */
public class MapIT {
    
    public MapIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setBase method, of class Map.
     */
    @Test
    public void testSetBase() {
        System.out.println("setBase");
        int i = 0;
        Map instance = new Map();
        instance.setBase(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBase method, of class Map.
     */
    @Test
    public void testGetBase() {
        System.out.println("getBase");
        Map instance = new Map();
        int expResult = 0;
        int result = instance.getBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConnection method, of class Map.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        int a = 0;
        int b = 0;
        int value = 0;
        Map instance = new Map();
        instance.setConnection(a, b, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCityById method, of class Map.
     */
    @Test
    public void testGetCityById() {
        System.out.println("getCityById");
        int i = 0;
        Map instance = new Map();
        City expResult = null;
        City result = instance.getCityById(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class Map.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        int a = 0;
        int b = 0;
        Map instance = new Map();
        int expResult = 0;
        int result = instance.getConnection(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCity method, of class Map.
     */
    @Test
    public void testAddCity() {
        System.out.println("addCity");
        City c = null;
        Map instance = new Map();
        instance.addCity(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Map.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Map instance = new Map();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCities method, of class Map.
     */
    @Test
    public void testGetCities() {
        System.out.println("getCities");
        Map instance = new Map();
        ArrayList<City> expResult = null;
        ArrayList<City> result = instance.getCities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
