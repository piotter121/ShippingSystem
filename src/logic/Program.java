/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import exceptions.IncorrectFileFormatException;
import inputOutput.Input;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int carsNumber;

    public Program() {
        loader = Input.getInstance();
        planner = new Planner();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Zbyt mało argumentów wejściowych");
            System.exit(-1);
        }
        Program p = new Program();
        p.initiateSystem(args[0], args[1], args[2]);
        //p.pickShipments();
        //p.sendCars();
    }

    public void initiateSystem(String... args) {
        loader.setMapFile(new File(args[0]));
        loader.setShipmentsListFile(new File(args[1]));
        try {
            loader.setCarsNumber(Integer.parseInt(args[2]));
            mainMap = loader.returnMap(); System.out.println("Wczytano mapę \n" + mainMap);
            ordersQueue = loader.returnShipmentsList(); System.out.println("Wczytano listę zleceń\n");
        } catch (NumberFormatException ex) {
            System.err.println("Nie prawidłowy argument jako liczba samochodów: " + args[2]);
            System.exit(-1);
        } catch (FileNotFoundException ex) {
            System.err.println("Nie znaleziono pliku " + args[0] + " zawierającego mapę");
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        } catch (IncorrectFileFormatException ex) {
            System.err.println("Nie pridłowy format jednego z plików wejściowych");
            System.exit(-1);
        }
        carsNumber = loader.returnCarsNumber(); System.out.println("Zainicowano liczbę samochodów: " + carsNumber);
        planner.setBase(loader.returnBase()); System.out.println("Ustalono bazę");
    }

    private void pickShipments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
