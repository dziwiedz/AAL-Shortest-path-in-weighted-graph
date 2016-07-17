package View;
import Controller.Controller;
import Controller.SideMenuController;
import Model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
public class SidePanel {

    private final Model model;
    private final SideMenuController sideMenuController;
    private JPanel sidePanel;

    JButton generateGraphButton;
    JButton startShortestPath1;
    JButton startShortestPath2;
    JButton startShortestPath3;
    JButton turnOnEditingButton;
    JButton turnOffEditingButton;
    JButton setStartPointButton;

    JButton editionPanelButton;
    JButton resultPanelButton;
    JButton algorithmsPanelButton;
    JButton testingPanelButton;

    JPanel topPanel;

    AlgorithmsPanel algorithmsPanel;
    EditionPanel editionPanel;
    ResultPanel resultPanel;
    TestingPanel testingPanel;


    public SidePanel(Model model)
    {
        sidePanel = new JPanel();
        this.model = model;
        this.sideMenuController = new SideMenuController(model, this);
        sidePanel.setPreferredSize(new Dimension(300,940));
       // this.setLayout(new BorderLayout());

        topPanel = new JPanel(new GridLayout(0,1,1,1));
        topPanel.setPreferredSize(new Dimension(300,200));
        algorithmsPanelButton = new JButton("Menu Wyboru Algorytmu");
        editionPanelButton = new JButton("Menu Edycji Rastra");
        resultPanelButton = new JButton("Menu Wynikow");
        testingPanelButton = new JButton("Menu Testowania");

        topPanel.add(algorithmsPanelButton);
        topPanel.add(editionPanelButton);
        topPanel.add(testingPanelButton);
        topPanel.add(resultPanelButton);
        sidePanel.add(topPanel,BorderLayout.NORTH);

        generateGraphButton = new JButton("Generuj Graf");
        startShortestPath1 = new JButton("BFS");
        startShortestPath2 = new JButton("Dijkstra");
        startShortestPath3 = new JButton("ALG3");
        turnOnEditingButton = new JButton("Wlacz edycje");
        turnOffEditingButton = new JButton("Wylacz edycje");
        algorithmsPanel = new AlgorithmsPanel(model);
        editionPanel = new EditionPanel(model);
        testingPanel = new TestingPanel(model);
        resultPanel = new ResultPanel(model);

        algorithmsPanel.getPanel().setVisible(false);
        editionPanel.getPanel().setVisible(false);
        testingPanel.getPanel().setVisible(false);


        sidePanel.add(algorithmsPanel.getPanel(),BorderLayout.CENTER);
        sidePanel.add(editionPanel.getPanel(),BorderLayout.CENTER);
        sidePanel.add(resultPanel.getPanel(),BorderLayout.CENTER);
        sidePanel.add(testingPanel.getPanel(), BorderLayout.CENTER);

        addActionListener();
    }

    public void addActionListener()
    {
        editionPanelButton.addActionListener(e -> sideMenuController.displayEditionPanel());
        resultPanelButton.addActionListener(e -> sideMenuController.displayResultPanel());
        algorithmsPanelButton.addActionListener(e -> sideMenuController.displayAlgorithmsPanel());
        testingPanelButton.addActionListener((e -> sideMenuController.displayTestingPanel()));
    }
    public void displayEditionPanel()
    {
        editionPanel.getPanel().setVisible(true);
        algorithmsPanel.getPanel().setVisible(false);
        testingPanel.getPanel().setVisible(false);
        resultPanel.getPanel().setVisible(false);
    }
    public void displayAlgorithmsPanel()
    {
        editionPanel.getPanel().setVisible(false);
        algorithmsPanel.getPanel().setVisible(true);
        testingPanel.getPanel().setVisible(false);
        resultPanel.getPanel().setVisible(false);
    }
    public void displayTestingPanel()
    {
        editionPanel.getPanel().setVisible(false);
        algorithmsPanel.getPanel().setVisible(false);
        testingPanel.getPanel().setVisible(true);
        resultPanel.getPanel().setVisible(false);
    }
    public void displayResultPanel()
    {
        editionPanel.getPanel().setVisible(false);
        algorithmsPanel.getPanel().setVisible(false);
        testingPanel.getPanel().setVisible(false);
        resultPanel.getPanel().setVisible(true);
    }
    public void addActionListenerToAlgorithmPanel(Controller controller)
    {
        algorithmsPanel.addActionListener(controller);
    }

    public JPanel getPanel()
    {
        return sidePanel;
    }
}
