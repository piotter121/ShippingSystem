/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import exceptions.IncorrectFileFormatException;
import java.io.*;
import java.util.Scanner;
import map.City;
import map.Map;
import order.Shipment;
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
    private int base = - 1;

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

    public ShipmentsList returnShipmentsList() throws FileNotFoundException, IncorrectFileFormatException {
        Scanner reader = new Scanner(shipmentsList);
        ShipmentsList list = new ShipmentsList();
        String[] splited;
        if (reader.hasNext()) {
            splited = reader.nextLine().split("\\s+");
            if (splited.length == 1) {
                try {
                    base = Integer.parseInt(splited[0]);
                } catch (NumberFormatException e) {
                    throw new IncorrectFileFormatException();
                }
            } else {
                throw new IncorrectFileFormatException();
            }
        }
        while (reader.hasNext()) {
            splited = reader.nextLine().split("\\s+");
            try {
                list.add(new Shipment(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),
                        Integer.parseInt(splited[2]), getShipmentName(splited), Integer.parseInt(splited[splited.length - 1])));
            } catch (NumberFormatException e) {
                System.out.print("Nie prawidłowa linia :");
                for (String s : splited) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }

        return list;
    }

    public int returnBase() {
        return base;
    }

    public Map returnMap() throws FileNotFoundException {
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
            try {
                if (splited.length == 2) {
                    map.addCity(new City(Integer.parseInt(splited[0]), splited[1]));
                } else if (splited.length == 3) {
                    map.setConnection(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),
                            Integer.parseInt(splited[2]));
                }
            } catch (NumberFormatException ex) {
                System.out.println("Pominięta linia: " + buffer);
            }
        }

        return map;
    }

    private String getShipmentName(String[] splited) {
        String result = new String();
        for (int i = 3; i < splited.length - 1; i++) {
            result += splited[i] + ' ';
        }

        return result;
    }

    public int returnCarsNumber() {
        return carsNumber;
    }
}
