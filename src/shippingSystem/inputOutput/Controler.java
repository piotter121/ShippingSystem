/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.inputOutput;

import shippingSystem.car.Car;
import java.io.PrintStream;
import shippingSystem.shipments.Shipment;

/**
 *
 * @author Piotrek
 */
public class Controler {

    private PrintStream outStr;
    private long startTime;

    public Controler(PrintStream o) {
        super();
        outStr = o;
        startTime = System.nanoTime();
    }

    public synchronized void startCall(Car c) {
        double relativeTime = 0;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("wyruszył z bazy " + c.positionCity() + "\n");
    }

    public synchronized void callRemovedShipment(Car c, Shipment s) {
        double relativeTime = 0;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dostarczył przesyłkę: " + s.getName());
        outStr.print(" do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callReachedDestination(Car c) {
        double relativeTime = 0;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callAddedShipment(Car c, Shipment s) {
        double relativeTime = 0;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("przyjął przesyłkę: " + s.getName() + "\n");
    }

}
