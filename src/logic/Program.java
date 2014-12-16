/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import car.Car;
import exceptions.IncorrectFileFormatException;
import inputOutput.Input;
import java.io.FileNotFoundException;
import map.Map;
import order.ShipmentsList;

/**
 *
 * @author Piotrek
 */
public class Program {

    private Input loader;
    private Planner planner;
    private Map mainMap;
    private ShipmentsList ordersQueue;
    private Car[] cars;
    private int carsNumber;

    public Program() {
        planner = new Planner();
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
            System.err.println("Nie pridłowy format jednego z plików wejściowych");
            System.exit(-1);
        }
        carsNumber = loader.returnCarsNumber();
        cars = new Car[carsNumber];
        for (int i = 0; i < carsNumber; i++) {
            cars[i] = new Car(loader.returnCarsCapacity());
        }
        planner.setBase(loader.returnBase());
    }

    private void startSystem() {
        planner.calculatePaths(mainMap);
        while (!ordersQueue.isEmpty()) {
            planner.pickShipmentsToCars(cars, ordersQueue);
        }
    }
}
