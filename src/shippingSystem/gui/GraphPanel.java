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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import org.apache.commons.collections15.Transformer;
import shippingSystem.car.Car;
import shippingSystem.car.Car.CarState;
import shippingSystem.map.City;
import shippingSystem.map.Map;

/**
 *
 * @author Piotrek
 */
public class GraphPanel extends JPanel implements Observer {

    private BasicVisualizationServer<City, Integer> vv;

    public GraphPanel(Graph g) {
        Layout<City, Integer> layout = new CircleLayout(new Map());
        layout.setSize(new Dimension(500, 400));
        vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(540, 440));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);

        add(vv);
    }

    public void setGraph(Graph g) {
        vv.getGraphLayout().setGraph(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        CarState state;
        state = (CarState) o;
        Car car;
        car = state.getCar();

        Transformer<City, Paint> vertexPaint = new Transformer<City, Paint>() {
            @Override
            public Paint transform(City i) {
                if (i.equals(car.positionCity())) {
                    return car.getColor();
                }
                return Color.RED;
            }
        };
    }

}
