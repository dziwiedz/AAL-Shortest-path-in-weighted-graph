package View;

import Controller.MouseListener;
import Model.*;

import javax.swing.*;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
public class BoardPanel extends JPanel {


    public static final int TILE_SIZE = 10;

    private Model model;
    /**
     * Konstruktor
     */
    public BoardPanel(Model model)
    {
        this.model = model;
        setBackground(Color.BLACK);
    }
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        Point point;
        for (int i=0; i< model.getVertexListSize();i++)
        {

            point=model.getVertexPoint(i);
            if (model.getTile(point.x, point.y) == TileType.START_POINT) g.setColor(Color.blue);
            else if (model.getTile(point.x, point.y) == TileType.END_POINT) g.setColor(Color.red);
                else if (model.getTile(point.x, point.y) == TileType.SHORTESTPATH) g.setColor(Color.green);
                    else g.setColor(Color.WHITE);
            g.fillRect(point.x*TILE_SIZE, point.y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        g.setColor(Color.DARK_GRAY);
       for(int x = 0; x < model.getN(); x++) {
            for(int y = 0; y < model.getM(); y++) {
                g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, model.getM()*TILE_SIZE);
                g.drawLine(0, y * TILE_SIZE, model.getN()*TILE_SIZE, y * TILE_SIZE);
            }
        }

    }
    public void changeSize(int x, int y)
    {
        setPreferredSize(new Dimension(x*TILE_SIZE,y*TILE_SIZE));
        //changeSize(x*TILE_SIZE,y*TILE_SIZE);
        revalidate();
        repaint();
    }

}
