/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import inputOutput.Input;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Map;

/**
 *
 * @author Piotrek
 */
public class InputTest {
    public static void main(String[] args) {
        Input i = Input.getInstance();
        i.setMapFile(new File("C:\\Users\\Piotrek\\Desktop\\mapa.txt"));
        Map m = new Map();
        try {
            m = i.returnMap();
        } catch (IOException ex) {
            Logger.getLogger(InputTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(m.toString());
    }
}
