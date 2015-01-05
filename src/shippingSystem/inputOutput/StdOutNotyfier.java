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

    public StdOutNotyfier() {
        super();
        outStr = System.out;
    }

    private synchronized void startCall(Car c) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("wyruszył z bazy " + c.positionCity() + "\n");
    }

    private synchronized void callRemovedShipment(Car c, Shipment s) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("dostarczył przesyłkę: " + s.getName());
        outStr.print(" do miasta: " + c.positionCity());
        outStr.print(" w czasie " + c.getTime() + "\n");
    }

    private synchronized void callReachedDestination(Car c) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    private synchronized void callAddedShipment(Car c, Shipment s) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("przyjął przesyłkę: " + s.getName() + "\n");
    }

    private synchronized void callFinishedTrace(Car c) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("dotarł do miasta: " + c.positionCity() + "\n");
    }

    private synchronized void callStopped(Car c) {
        outStr.print("Samochód nr " + c.getCarId() + " ");
        outStr.print("znajduje się w bazie: " + c.positionCity() + "\n");
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        CarState state;
        state = (CarState) o;
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
