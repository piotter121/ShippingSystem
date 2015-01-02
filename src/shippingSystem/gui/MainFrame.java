/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import shippingSystem.logic.Program;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class MainFrame extends JFrame {

    private JPanel panel;
    private BasicVisualizationServer<String, String> vv;
    private Program system = new Program();

    public MainFrame(String... args) {
        system.initiateSystem(args);
        SparseMultigraph<String, String> smg = null;
        Layout<String, String> layout = new CircleLayout((SparseMultigraph) system.loader.returnMap());
        layout.setSize(new Dimension(500, 400));
        vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(540, 440));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);
        panel.add(vv);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 804, 653);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame window = new MainFrame(args);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
