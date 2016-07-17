package View;
import Controller.Controller;
import Controller.MouseListener;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
public class View {
    private Model model;
    private JFrame frame;
    private BoardPanel board;
    private SidePanel side;
    JScrollPane scrollPane ;

    public View(Model m)
    {
        this.model = m;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board = new BoardPanel(model);
        side = new SidePanel(model);
        scrollPane = new JScrollPane(board);
        scrollPane.setPreferredSize(new Dimension(1440,900));
        frame.setLayout(new BorderLayout());
        frame.setTitle("Shortest Path");


        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(side.getPanel(), BorderLayout.EAST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     *
     * @param l
     */
    public void addMouseListener(final MouseListener l)
    {
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                l.checkPoint(e);
                board.repaint();

            }
        });
    }

    public void addBoardListner(Controller controller)
    {
        side.addActionListenerToAlgorithmPanel(controller);
    }
    public void repaintBoard()
    {

        board.repaint();
        board.changeSize(model.getN(),model.getM());
    }
}
