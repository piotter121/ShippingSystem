/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import shippingSystem.car.Car;
import shippingSystem.shipments.Shipment;

/**
 *
 * @author Piotrek
 */
public class ComPanel extends JPanel implements Observer {

    private JTextArea communicatesArea;
    private JLabel communicatesLabel;
    private JScrollPane scroll;

    public ComPanel() {
        super();
        setLayout(new BorderLayout());
        communicatesLabel = new JLabel("Komunikaty");
        add(communicatesLabel, BorderLayout.PAGE_START);

        communicatesArea = new JTextArea(new String(), 20, 30);
        scroll = new JScrollPane(communicatesArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
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

    private synchronized void callStopped(Car car) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " ");
        communicatesArea.append("znajduje się w bazie: " + car.positionCity() + "\n");
    }

    private synchronized void callAddedShipment(Car car, Shipment shipment) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " ");
        communicatesArea.append("przyjął przesyłkę: " + shipment.getName() + "\n");
    }

    private synchronized void startCall(Car car) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " ");
        communicatesArea.append("wyruszył z bazy " + car.positionCity() + "\n");
    }

    private synchronized void callReachedDestination(Car car) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " ");
        communicatesArea.append("dotarł do miasta: " + car.positionCity() + "\n");
    }

    private synchronized void callRemovedShipment(Car car, Shipment shipment) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " "
                + "dostarczył przesyłkę: " + shipment.getName()
                + " do miasta: " + car.positionCity()
                + " w czasie " + car.getTime() + "\n");
    }

    private synchronized void callFinishedTrace(Car car) {
        communicatesArea.append("Samochód nr " + car.getCarId() + " "
                + "dotarł do miasta: " + car.positionCity() + "\n");
    }

}
