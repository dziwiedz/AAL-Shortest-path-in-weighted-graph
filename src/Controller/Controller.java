package Controller;
import Model.*;
import View.*;

import java.awt.event.MouseEvent;

import static View.BoardPanel.TILE_SIZE;

/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
public class Controller implements MouseListener {

    Model model;
    View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        view.addBoardListner(this);
        view.addMouseListener(this);
    }

    public void repaintBoard()
    {
        view.repaintBoard();
    }
    @Override
    public void addPoint(MouseEvent e) {
        if(model.getMode()) model.addPoint(e.getPoint());
    }

    @Override
    public void removePoint(MouseEvent e) {
        if (model.getMode()) model.removePoint(e.getPoint());
    }

    @Override
    public void checkPoint(MouseEvent e)
    {
        if (model.checkPoint(e.getPoint()))
        {
            if (model.getMode())
            {
                if ( model.getTile(e.getX()/TILE_SIZE, e.getY()/TILE_SIZE) == null) addPoint(e);
                else removePoint(e);
            }
            else if (model.getSettingStartPoint())
            {
                model.setAsStartPoint(e.getPoint());

            }
            else if (model.getSettingEndPoint())
            {
                model.setAsEndPoint(e.getPoint());

            }
            repaintBoard();
        }
    }
}
