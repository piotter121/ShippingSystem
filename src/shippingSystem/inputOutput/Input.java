/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.inputOutput;

import shippingSystem.exceptions.CityNotFoundException;
import shippingSystem.exceptions.IncorrectFileFormatException;
import java.io.*;
import java.util.Scanner;
import shippingSystem.map.City;
import shippingSystem.map.Map;
import shippingSystem.shipments.Shipment;
import shippingSystem.shipments.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Input {

    private File mapFile;
    private File shipmentsListFile;
    private int carsNumber;
    private int base = - 1;
    private int capacity;
    private Map processedMap;
    private ShipmentsList ordersList;

    public Input() {
        mapFile = null;
        shipmentsListFile = null;
        processedMap = null;
        ordersList = null;
        carsNumber = 0;
        capacity = 0;
    }

    public void setShipmentListFile(File file) {
        shipmentsListFile = file;
    }
    
    public ShipmentsList returnShipmentsList() throws FileNotFoundException, IncorrectFileFormatException {
        processedMap = returnMap();
        if (processedMap != null && ordersList == null && shipmentsListFile != null) {
            Scanner reader = new Scanner(shipmentsListFile);
            ordersList = new ShipmentsList();
            String[] splited;
            if (reader.hasNext()) {
                splited = reader.nextLine().split("\\s+");
                if (splited.length == 1) {
                    try {
                        int tmp = Integer.parseInt(splited[0]);
                        if (processedMap.getCityById(tmp) != null) {
                            base = tmp;
                            processedMap.setBase(tmp);
                        } else {
                            throw new CityNotFoundException();
                        }
                    } catch (NumberFormatException | CityNotFoundException e) {
                        throw new IncorrectFileFormatException();
                    }
                } else {
                    throw new IncorrectFileFormatException();
                }
            } else {
                throw new IncorrectFileFormatException();
            }
            while (reader.hasNext()) {
                splited = reader.nextLine().split("\\s+");
                try {
                    int cityA = Integer.parseInt(splited[1]);
                    int cityB = Integer.parseInt(splited[2]);
                    if (processedMap.getCityById(cityA) != null && processedMap.getCityById(cityB) != null) {
                        ordersList.add(new Shipment(Integer.parseInt(splited[0]), cityA,
                                cityB, getShipmentName(splited), Integer.parseInt(splited[splited.length - 1])));
                    } else {
                        throw new CityNotFoundException();
                    }
                } catch (NumberFormatException | CityNotFoundException e) {
                    System.err.print("Nie prawidłowa linia: ");
                    for (String s : splited) {
                        System.err.print(s + " ");
                    }
                    System.err.println();
                }
            }
        }
        return ordersList;
    }

    public int returnBase() {
        return base;
    }

    public void setMapFile(File file) {
        mapFile = file;
    }
    
    public Map returnMap() throws FileNotFoundException {
        if (processedMap == null && mapFile != null) {
            Scanner reader = new Scanner(mapFile);
            processedMap = new Map();
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
                        processedMap.addCity(new City(Integer.parseInt(splited[0]), splited[1]));
                    } else if (splited.length == 3) {
                        int a = Integer.parseInt(splited[0]);
                        int b = Integer.parseInt(splited[1]);
                        processedMap.setConnection(a, b,
                                Integer.parseInt(splited[2]));
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Pominięta linia: " + buffer);
                }
            }
        }
        return processedMap;
    }

    private String getShipmentName(String[] splited) {
        String result = new String();
        for (int i = 3; i < splited.length - 1; i++) {
            result += splited[i] + ' ';
        }

        return result;
    }
    
    public void setCarsNumber(int number) {
        carsNumber = number;
    }

    public int returnCarsNumber() {
        return carsNumber;
    }

    public void setCarsCapacity(int number) {
        capacity = number;
    }
    
    public int returnCarsCapacity() {
        return capacity;
    }
}
