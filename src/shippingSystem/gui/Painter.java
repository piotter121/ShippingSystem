/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import shippingSystem.car.Car;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */

/* Wyświetlanie Ośki proto*/
public class Painter extends JPanel {

    public Map map;
    public Car[] cars;
    public int radius = 10;

    public Painter(Map map, Car[] cars) {
        this.map = map;
        this.cars = cars;
    }

    @Override
    public void paintComponent(Graphics g) {
        BasicStroke x = new BasicStroke(3);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(x);
        super.paintComponent(g);
        for (int i = 0; i < map.getCities().size(); i++) {
            g.setColor(map.getCityById(i).getColor());
            int x1 = map.getCityById(i).position.x;
            int y1 = map.getCityById(i).position.y;
            drawCircle(g, x1, y1, radius);
            g.setColor(Color.RED);
            g.drawString(String.valueOf(map.getCityById(i).getName()), x1, y1 - 25);
            for (int j = 0; j < map.getCityById(i).getSize(); j++) {
                g.setColor(map.getConnection(i,j).getColor());
                int x2 = map.getCity(map.getCityById(i).getConnection(j).getCityId()).position.x;
                int y2 = map.getCity(map.getCity(i).getConnection(j).getCityId()).position.y;
                g2.drawLine(x1, y1, x2, y2);
                g.drawString(String.valueOf(map.getCity(i).getConnection(j).getDistance()), (x1 + x2) / 2 + 10, (y1 + y2) / 2 - 10);
            }
        }
    }

    private void drawCircle(Graphics g, int x, int y, int radius) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
