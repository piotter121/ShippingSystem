/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingSystem.gui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import org.apache.commons.collections15.Transformer;
import shippingSystem.car.Car;
import static shippingSystem.car.Car.CarRecentState.ReachedDestination;
import shippingSystem.car.Car.CarState;
import shippingSystem.map.City;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class GraphPanel extends JPanel implements Observer {

    private BasicVisualizationServer<City, Integer> vv;
    private Graph g;

    public GraphPanel(Graph g) {
        this.g = g;
        setLayout(new FlowLayout());
        setSize(new Dimension(500, 400));

        Layout<City, Integer> layout = new CircleLayout(g);
        layout.setSize(new Dimension(500, 400));
        vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(540, 440));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.SE);

        add(vv);
        vv.repaint();
    }

    public void setGraph(Graph g) {
        this.g = g;
        vv.getGraphLayout().setGraph(g);
        vv.repaint();
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        CarState state;
        state = (CarState) o;
        Car car;
        car = state.getCar();

        switch (state.state) {
            case ReachedDestination:
                pickVeretex(car.positionCity());
                break;
        }

//        float dash[] = {10.0f};
//        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
//        Transformer<Integer, Stroke> edgeStrokeTransformer = new Transformer<Integer, Stroke>() {
//            @Override
//            public Stroke transform(Integer s) {
//                if (s == car) {
//                    return edgeStroke;
//                }
//                return new BasicStroke();
//            }
//        };
    }

    private void updateCarOnMap(Car car) {
        Transformer<City, Paint> vertexPaint = new Transformer<City, Paint>() {
            @Override
            public Paint transform(City i) {
                if (i.equals(car.positionCity())) {
                    return car.getColor();
                } else {
                    return Color.RED;
                }
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.repaint();
    }

    private void pickVeretex(City c) {
        vv.getRenderContext().getPickedVertexState().pick(c, true);
        vv.repaint();
    }
}
