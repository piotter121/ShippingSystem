/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Piotrek
 */
public class InputArgumentsPanel extends JPanel {

    private JLabel mapFileInfo,
            shipmentsListInfo;
    private JPanel carsNumberInfo,
            carsCapacityInfo;
    private JTextField carsNumber, carsCapacity;
    private JLabel cars, capacity;
    
    public InputArgumentsPanel() {
        super();

        setLayout(new FlowLayout());
        
        mapFileInfo = new JLabel();
        shipmentsListInfo = new JLabel();
        carsNumberInfo = new JPanel();
        carsCapacityInfo = new JPanel();
        carsNumber = new JTextField("0");
        carsCapacity = new JTextField("0");
        cars = new JLabel("Liczba samochodów");
        capacity = new JLabel("Pojemność samochodów");
        
        carsNumber.setSize(10, 20);
        carsCapacity.setSize(10, 20);
        
        carsNumberInfo.setLayout(new FlowLayout());
        carsCapacityInfo.setLayout(new FlowLayout());
        
        carsNumberInfo.add(cars);
        carsNumberInfo.add(carsNumber);
        
        carsCapacityInfo.add(capacity);
        carsCapacityInfo.add(carsCapacity);
        
        add(mapFileInfo);
        add(shipmentsListInfo);
        add(carsNumberInfo);
        add(carsCapacityInfo);
    }

    public void setCarsNumber(int i) {
        carsNumber.setText(String.valueOf(i));
    }
    
    public void setCarsCapacity(int i) {
        carsCapacity.setText(String.valueOf(i));
    }
    
    public int getCarsNumber() {
        return Integer.parseInt(carsNumber.getText());
    }
    
    public int getCarsCapacity() {
        return Integer.parseInt(carsCapacity.getText());
    }
    
    public void setMapFileInfo(String s) {
        mapFileInfo.setText(s);
    }
    
    public void setShipmentsFileInfo(String s) {
        shipmentsListInfo.setText(s);
    }
    
}
