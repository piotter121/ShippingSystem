/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import map.City;
import map.Map;

/**
 *
 * @author Piotrek
 */
public class Input {

    private File mapFile = null;
    private File shipmentsList = null;
    private int carsNumber = 0;

    public void setMapFile(File file) {
        mapFile = file;
    }

    public void setShipmentsListFile(File file) {
        shipmentsList = file;
    }

    public Map returnMap() throws IOException {
        Scanner reader = new Scanner(mapFile);
        Map map = null;
        ArrayList<City> cities;
        String buffer = reader.nextLine();
        String splited[] = buffer.split("//s+");
        if (splited[0].equals("#")) {
            if (splited[1].equals("miasta")) {
                cities = makeCities(reader);
                map = new Map((City[]) cities.toArray());
            } else if (splited[1].equals("połączenia")) {
                makeConnections(reader, map);
            }
        }

        return map;
    }

    private ArrayList<City> makeCities(Scanner reader) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void makeConnections(Scanner reader, Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
