/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import shippingSystem.exceptions.UnsignedInputFiles;
import shippingSystem.logic.Program;
import shippingSystem.map.City;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class MainFrame extends JFrame {

    private BasicVisualizationServer<City, Integer> vv;
    private Program system;
    private JPanel rightPanel;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMapFileMenuItem,
            openListFileMenuItem,
            exitMenuItem;
    private JButton startButton;

    private final JFileChooser fileChooser;

    public MainFrame() {
        super();
        
        system = new Program();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLayout(new FlowLayout());
        setTitle("ShippingSystem");

        fileChooser = new JFileChooser();

        initUpMenu();
        leftPanel();
        rightPanel = new ComPanel();

        startButton = new JButton("Start");
        startButton.addActionListener((ActionEvent e) -> {
            startProgram();
        });
        rightPanel.add(startButton, BorderLayout.PAGE_END);

        setJMenuBar(menuBar);
        add(vv);
        add(rightPanel);

    }

    private void initUpMenu() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("Menu");
        openMapFileMenuItem = new JMenuItem("Otwórz plik z mapą");
        openMapFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File opened = fileChooser.getSelectedFile();
                    system.systemInput.setMapFile(opened);
                }
            }
        });
        openListFileMenuItem = new JMenuItem("Otwórz plik z listą zleceń");
        openListFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File opened = fileChooser.getSelectedFile();
                    system.systemInput.setShipmentListFile(opened);
                }
            }
        });
        exitMenuItem = new JMenuItem("Wyjście");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        menuBar.add(fileMenu);
        fileMenu.add(openMapFileMenuItem);
        fileMenu.add(openListFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }

    private void leftPanel() {
        Layout<City, Integer> layout = new CircleLayout(new Map());
        layout.setSize(new Dimension(500, 400));
        vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(540, 440));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);
    }

    private void startProgram() {
        try {
            system.initiateSystem();
        } catch (UnsignedInputFiles ex) {
            JOptionPane.showMessageDialog(this, "Nie zainicjalizowano systemu", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        system.setObservers((Observer) rightPanel);
        system.startSystem();
    }

}
