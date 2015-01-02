/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import shippingSystem.car.Car;
import shippingSystem.shipments.Shipment;

/**
 *
 * @author Piotrek
 */
public class RightPanel extends JPanel implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Car.CarState state;
        state = (Car.CarState) o;

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
