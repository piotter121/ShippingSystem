/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.logic;

import shippingSystem.inputOutput.Controler;
import shippingSystem.car.Car;
import shippingSystem.exceptions.IncorrectFileFormatException;
import shippingSystem.inputOutput.Input;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import shippingSystem.map.Map;
import shippingSystem.shipments.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Program {

    private Input loader;
    private Planner planner;
    private Controler observer;
    private Map mainMap;
    private ShipmentsList ordersQueue;
    private Car[] cars;
    private int carsNumber;

    public Program() {
        planner = new Planner();
        observer = new Controler(System.out);
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Zbyt mało argumentów wejściowych");
            System.exit(-1);
        }
        Program p = new Program();
        p.initiateSystem(args);
        p.startSystem();
    }

    public void initiateSystem(String... args) {
        loader = new Input(args[0], args[1], args[2], args[3]);
        try {
            mainMap = loader.returnMap();
            ordersQueue = loader.returnShipmentsList();
        } catch (FileNotFoundException ex) {
            System.err.println("Nie znaleziono pliku " + args[0] + " zawierającego mapę");
            System.exit(-1);
        } catch (IncorrectFileFormatException ex) {
            System.err.println("Nie prawidłowy format jednego z plików wejściowych");
            System.exit(-1);
        }
        carsNumber = loader.returnCarsNumber();
        cars = new Car[carsNumber];
        for (int i = 0; i < carsNumber; i++) {
            cars[i] = new Car(loader.returnCarsCapacity());
            cars[i].addObserver(observer);
            cars[i].addMap(mainMap);
        }
        mainMap.setBase(loader.returnBase());
    }

    public void startSystem() {
        planner.calculatePaths(mainMap);
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
            if (!c.isAlive() && c.hasShipment()) {
                c.start();
            }
        }
    }
}
