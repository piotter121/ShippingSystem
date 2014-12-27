/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import exceptions.CityNotFoundException;
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

    private File mapFile;
    private File shipmentsList;
    private int carsNumber;
    private int base = - 1;
    private int capacity;
    private Map processedMap;
    private ShipmentsList ordersList;

    public Input(String map, String list, String cars, String capacity) throws IllegalArgumentException {
        mapFile = new File(map);
        shipmentsList = new File(list);
        this.processedMap = null;
        this.ordersList = null;
        try {
            carsNumber = Integer.parseInt(cars);
            this.capacity = Integer.parseInt(capacity);
        } catch (NumberFormatException x) {
            throw new IllegalArgumentException();
        }
    }

    public ShipmentsList returnShipmentsList() throws FileNotFoundException, IncorrectFileFormatException {
        processedMap = returnMap();
        if (ordersList == null) {
            Scanner reader = new Scanner(shipmentsList);
            ordersList = new ShipmentsList();
            String[] splited;
            if (reader.hasNext()) {
                splited = reader.nextLine().split("\\s+");
                if (splited.length == 1) {
                    try {
                        int tmp = Integer.parseInt(splited[0]);
                        if (processedMap.getCityById(tmp) != null) {
                            base = tmp;
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

    public Map returnMap() throws FileNotFoundException {
        if (processedMap == null) {
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

    public int returnCarsNumber() {
        return carsNumber;
    }

    public int returnCarsCapacity() {
        return capacity;
    }
}
