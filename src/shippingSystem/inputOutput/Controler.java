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

    public Controler(PrintStream o) {
        super();
        outStr = o;
    }

    public synchronized void startCall(Car c) {
        outStr.print(c.getTime() + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("wyruszył z bazy " + c.positionCity() + "\n");
    }

    public synchronized void callRemovedShipment(Car c, Shipment s) {
        outStr.print(c.getTime() + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dostarczył przesyłkę: " + s.getName());
        outStr.print(" do miasta: " + c.positionCity());
        outStr.print(" w czasie " + c.getTime());
    }

    public synchronized void callReachedDestination(Car c) {
        outStr.print(c.getTime() + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callAddedShipment(Car c, Shipment s) {
        outStr.print(c.getTime() + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("przyjął przesyłkę: " + s.getName() + "\n");
    }

    public synchronized void callFinishedTrace(Car c) {
        outStr.print(c.getTime() + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

}
