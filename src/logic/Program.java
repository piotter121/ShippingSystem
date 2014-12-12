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
    public static void main(String[] args) {
        Input loader = Input.getInstance();        
        Planner planner = new Planner();
        Map mainMap;
        ShipmentsList ordersQueue;
        int carsNumber;
        
        loader.setMapFile(new File(args[0]));
        loader.setShipmentsListFile(new File(args[1]));
        loader.setCarsNumber(Integer.parseInt(args[2]));
        
        try {
            mainMap = loader.returnMap();
            ordersQueue = loader.returnShipmentsList();
        } catch (FileNotFoundException ex) {
            System.err.println("Nie znaleziono pliku " + args[0] + " zawierającego mapę");
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        } catch (IncorrectFileFormatException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        carsNumber = loader.returnCarsNumber();
        planner.setBase(loader.returnBase());
    }
}
