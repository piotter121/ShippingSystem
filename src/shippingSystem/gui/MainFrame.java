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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import shippingSystem.exceptions.IncorrectInputArguments;
import shippingSystem.logic.Program;
import shippingSystem.map.City;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class MainFrame extends JFrame {

    private Program system;
    private ComPanel rightPanel;
    private InputArgumentsPanel argumentPanel;
    private GraphPanel graph;

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
        rightPanel = new ComPanel();
        argumentPanel = new InputArgumentsPanel();
        graph = new GraphPanel(new Map());

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!system.isRunning) {
                    Thread main = new Thread("Main") {
                        @Override
                        public void run() {
                            startProgram();
                        }
                    };
                    main.start();
                }
            }
        });
        rightPanel.add(startButton, BorderLayout.PAGE_END);

        setJMenuBar(menuBar);
        add(graph);
        add(rightPanel);
        add(argumentPanel);
    }

    private void initUpMenu() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("Menu");
        openMapFileMenuItem = new JMenuItem("Otwórz plik z mapą");
        openMapFileMenuItem.addActionListener((ActionEvent e) -> {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File opened = fileChooser.getSelectedFile();
                system.systemInput.setMapFile(opened);
                argumentPanel.setMapFileInfo(opened.getPath());
            }
        });
        openListFileMenuItem = new JMenuItem("Otwórz plik z listą zleceń");
        openListFileMenuItem.addActionListener((ActionEvent e) -> {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File opened = fileChooser.getSelectedFile();
                system.systemInput.setShipmentListFile(opened);
                argumentPanel.setShipmentsFileInfo(opened.getPath());
            }
        });
        exitMenuItem = new JMenuItem("Wyjście");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        exitMenuItem.addActionListener((ActionEvent e) -> {
            dispose();
        });

        menuBar.add(fileMenu);
        fileMenu.add(openMapFileMenuItem);
        fileMenu.add(openListFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }

    private void startProgram() {
        system.systemInput.setCarsNumber(argumentPanel.getCarsNumber());
        system.systemInput.setCarsCapacity(argumentPanel.getCarsCapacity());
        try {
            system.initiateSystem();
        } catch (IncorrectInputArguments ex) {
            JOptionPane.showMessageDialog(this, "Nie zainicjalizowano systemu",
                    "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        system.setObservers((Observer) rightPanel);
        graph.setGraph(system.getMap());
        system.startSystem();

    }

}
