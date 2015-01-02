/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import shippingSystem.logic.Program;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class MainFrame extends JFrame {

    private BasicVisualizationServer<String, String> vv;
    private Program system = new Program();
    private JPanel rightPanel;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMapFileMenuItem,
            openListFileMenuItem,
            exitMenuItem;
    private JTextArea comunicates;

    private final JFileChooser fileChooser;

    public MainFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(804, 653);
        setLayout(new FlowLayout());
        setTitle("ShippingSystem");

        fileChooser = new JFileChooser();

        initUpMenu();
        leftPanel();
        rightPanel();
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
                throw new UnsupportedOperationException();
//                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//                    File opened = fileChooser.getSelectedFile();
//
//                }
            }
        });
        openListFileMenuItem = new JMenuItem("Otwórz plik z listą zleceń");
        openListFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException();
//                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//                    File save = fileChooser.getSelectedFile();
//                }
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

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(openMapFileMenuItem);
        fileMenu.add(openListFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }

    private void leftPanel() {
        Layout<String, String> layout = new CircleLayout((SparseMultigraph) new Map());
        layout.setSize(new Dimension(500, 400));
        vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(540, 440));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);
        add(vv);

        vv.setVisible(true);
    }

    private void rightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());

        comunicates = new JTextArea(" ", 20, 30);
        comunicates.setVisible(true);
        rightPanel.add(comunicates);

        rightPanel.setVisible(true);
    }
}
