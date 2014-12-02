/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.*;
import java.util.Scanner;
import map.City;
import map.Map;
import order.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Input {

    private final static Input instance = new Input();

    private File mapFile = null;
    private File shipmentsList = null;
    private int carsNumber = 0;

    private Input() {
    }

    public static Input getInstance() {
        return instance;
    }

    public void setMapFile(File file) {
        mapFile = file;
    }

    public void setShipmentsListFile(File file) {
        shipmentsList = file;
    }

    public void setCarsNumber(int i) {
        carsNumber = i;
    }
    
    public ShipmentsList returnShipmentsList() throws FileNotFoundException {
        Scanner reader = new Scanner(shipmentsList);
        ShipmentsList list = new ShipmentsList();        
        
        return list;
    }

    public Map returnMap() throws IOException {
        Scanner reader = new Scanner(mapFile);
        Map map = new Map();
        String buffer;
        String splited[];
        while (reader.hasNext()) {
            buffer = reader.nextLine();
            splited = buffer.split("\\s+");
            if (splited[0].equals("#")) {
                continue;
            }
            if (splited.length == 2) {
                map.addCity(new City(Integer.parseInt(splited[0]), splited[1]));
            } else if (splited.length == 3) {
                map.setConnection(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),
                        Integer.parseInt(splited[2]));
            }
        }

        return map;
    }

}
