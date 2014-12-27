/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import car.Car;
import java.io.PrintStream;
import order.Shipment;

/**
 *
 * @author Piotrek
 */
public class Observer {

    private PrintStream outStr;
    private long startTime;

    public Observer(PrintStream o) {
        super();
        outStr = o;
        startTime = System.nanoTime();
    }

    public synchronized void startCall(Car c) {
        long relativeTime = System.nanoTime() - startTime;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("wyruszył z bazy\n");
    }

    public synchronized void callRemovedShipment(Car c, Shipment s) {
        long relativeTime = System.nanoTime() - startTime;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dostarczył przesyłkę: " + s.getName());
        outStr.print(" do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callReachedDestination(Car c) {
        long relativeTime = System.nanoTime() - startTime;
        outStr.print(relativeTime + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }
}
