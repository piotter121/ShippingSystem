/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.logic;

import java.io.File;
import shippingSystem.car.Car;
import shippingSystem.exceptions.IncorrectFileFormatException;
import shippingSystem.inputOutput.Input;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shippingSystem.exceptions.IncorrectInputArguments;
import shippingSystem.inputOutput.StdOutNotyfier;
import shippingSystem.map.Map;
import shippingSystem.shipments.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Program {

    private Planner planner;
    private ArrayList<Observer> obs;
    private Map mainMap;
    private ShipmentsList ordersQueue;
    private Car[] cars;
    private int carsNumber;

    public Input systemInput;

    public Program() {
        obs = new ArrayList<>();
        systemInput = new Input();
    }

    public void setObservers(Observer... obs) {
        for (Observer o : obs) {
            for (Car c : cars) {
                c.addObserver(o);
            }
        }
    }

    public Map getMap() {
        return mainMap;
    }

    public ShipmentsList getShipmentsList() {
        return ordersQueue;
    }

    public void initiateSystem() throws IncorrectInputArguments {
        try {
            mainMap = systemInput.returnMap();
            mainMap.setBase(systemInput.returnBase());
            ordersQueue = systemInput.returnShipmentsList();
        } catch (FileNotFoundException ex) {
            System.err.println("Nie znaleziono pliku zawierającego mapę");
            System.exit(-1);
        } catch (IncorrectFileFormatException ex) {
            System.err.println("Nie prawidłowy format jednego z plików wejściowych");
            System.exit(-1);
        }
        carsNumber = systemInput.returnCarsNumber();
        int capacity = systemInput.returnCarsCapacity();
        if (mainMap == null || ordersQueue == null || carsNumber < 1 || capacity < 1) {
            throw new IncorrectInputArguments();
        }
        planner = new Planner(mainMap);
        cars = new Car[carsNumber];
        for (int i = 0; i < carsNumber; i++) {
            cars[i] = new Car(capacity);
            cars[i].addMap(mainMap);
        }
    }

    public void startSystem() {
        planner.calculatePaths();
        while (!ordersQueue.isEmpty()) {
            planner.pickShipmentsToCars(cars, ordersQueue);
            startRestOfCars();
            for (Car e : cars) {
                try {
                    e.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(-2);
                }
            }
        }
    }

    private synchronized void startRestOfCars() {
        for (Car c : cars) {
            if (c.isAvailable() && c.hasShipment()) {
                c.start();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Zbyt mało argumentów wejściowych");
            System.exit(-4);
        }
        Program p = new Program();
        p.systemInput.setMapFile(new File(args[0]));
        p.systemInput.setShipmentListFile(new File(args[1]));
        p.systemInput.setCarsNumber(Integer.parseInt(args[2]));
        p.systemInput.setCarsCapacity(Integer.parseInt(args[3]));
        try {
            p.initiateSystem();
        } catch (IncorrectInputArguments ex) {
            System.err.println("Nie wczytano argumentów wejściowych");
        }
        p.setObservers(new StdOutNotyfier());
        p.startSystem();
    }
}
