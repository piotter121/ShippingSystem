/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.inputOutput;

import shippingSystem.car.Car;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import shippingSystem.car.Car.CarState;
import shippingSystem.shipments.Shipment;

/**
 *
 * @author Piotrek
 */
public class StdOutNotyfier implements Observer {

    private PrintStream outStr;
    private long startTime;
    double time;

    public StdOutNotyfier() {
        super();
        startTime = 0;
        time = 0.0;
        outStr = System.out;
    }

    public synchronized void startCall(Car c) {
        startTime = System.nanoTime();
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("wyruszył z bazy " + c.positionCity() + "\n");
    }

    public synchronized void callRemovedShipment(Car c, Shipment s) {
        outStr.print(time + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dostarczył przesyłkę: " + s.getName());
        outStr.print(" do miasta: " + c.positionCity());
        outStr.print(" w czasie " + c.getTime() + "\n");
    }

    public synchronized void callReachedDestination(Car c) {
        outStr.print(time + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callAddedShipment(Car c, Shipment s) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("przyjął przesyłkę: " + s.getName() + "\n");
    }

    public synchronized void callFinishedTrace(Car c) {
        outStr.print(time + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    public synchronized void callStopped(Car c) {
        outStr.print(time + " ");
        outStr.print(": Samochód nr " + c.getCarId() + " ");
        outStr.print("znajduje się w bazie: " + c.positionCity() + "\n");
    }

    @Override
    public void update(Observable o, Object arg) {
        CarState state;
        state = (CarState) o;
        time = (System.nanoTime() - startTime) / 1000;
        switch (state.state) {
            case Stopped:
                callStopped(state.getCar());
                break;
            case AddedShipment:
                callAddedShipment(state.getCar(), (Shipment) arg);
                break;
            case StartedDelivery:
                startCall(state.getCar());
                break;
            case ReachedDestination:
                callReachedDestination(state.getCar());
                break;
            case RemovedShipment:
                callRemovedShipment(state.getCar(), (Shipment) arg);
                break;
            case FinishedTrace:
                callFinishedTrace(state.getCar());
                break;
        }
    }

}
