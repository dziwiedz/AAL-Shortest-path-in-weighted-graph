package View;
import Controller.TestingPanelController;
import Model.Model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class TestingPanel {
    private final Model model;
    private final TestingPanelController testingPanelController;
    private JPanel testingPanel;

    private JPanel dataPanel;
    private JPanel checkBoxPanel;
    private JPanel labelsPanel;
    private JPanel fieldsPanel;
    private JPanel runPanel;

    private JTextField nValue;
    private JTextField mValue;
    private JTextField startVertexValue;
    private JTextField incrementValue;
    private JTextField trialsNumber;

    private JCheckBox genereateGraphButton;
    private JCheckBox bfsButton;
    private JCheckBox dijkstraButton;
    private JComboBox pointsChoosingModeList;

    private JLabel statusInfoLabel;

    private JButton startButton;


    public TestingPanel(Model model)
    {
        testingPanel = new JPanel();
        this.model = model;
        this.testingPanelController = new TestingPanelController(model, this);
        testingPanel.setLayout(new GridLayout(0,1,1,1));
        testingPanel.setBorder(new TitledBorder("Testowanie"));
        testingPanel.setPreferredSize(new Dimension(300,350));

        dataPanel = new JPanel(new BorderLayout(2,2));
        dataPanel.setBorder(new TitledBorder("Dane"));

        labelsPanel = new JPanel(new GridLayout(0,1,1,1));
        labelsPanel.add(new JLabel("Wartosc N:"));
        labelsPanel.add(new JLabel("Wartosc M:"));
        labelsPanel.add(new JLabel("Wartosc pocztkowa n:"));
        labelsPanel.add(new JLabel("O ile zwiekszac n:"));
        labelsPanel.add(new JLabel("Ilosc prob :"));

        dataPanel.add(labelsPanel,BorderLayout.CENTER);

        fieldsPanel = new JPanel(new GridLayout(0,1,1,1));
        fieldsPanel.setPreferredSize(new Dimension(100,150));
        nValue = new JTextField();
        mValue = new JTextField();
        startVertexValue = new JTextField();
        incrementValue = new JTextField();
        trialsNumber = new JTextField();
        fieldsPanel.add(nValue);
        fieldsPanel.add(mValue);
        fieldsPanel.add(startVertexValue);
        fieldsPanel.add(incrementValue);
        fieldsPanel.add(trialsNumber);

        dataPanel.add(fieldsPanel,BorderLayout.EAST);

        checkBoxPanel = new JPanel(new GridLayout(0,1,1,1));
        checkBoxPanel.setBorder(new TitledBorder("Algorytmy:"));
        //genereateGraphButton = new JCheckBox("Generowanie Grafu");
//        checkBoxPanel.add(genereateGraphButton);
        bfsButton = new JCheckBox("BFS");
        checkBoxPanel.add(bfsButton);
        dijkstraButton = new JCheckBox("Dijkstra");
        checkBoxPanel.add(dijkstraButton);

        checkBoxPanel.add(new JLabel("Wybieranie punktow sciezki:"));
        pointsChoosingModeList = new JComboBox();
        pointsChoosingModeList.addItem("Losuj punkt startowy i koncowy");
        pointsChoosingModeList.addItem("Ustaw na pierwszy i ostatni wierzcholek listy");
        pointsChoosingModeList.addItem("Ustaw wierzchołki o maksymalnej odległości");
        checkBoxPanel.add(pointsChoosingModeList);

        runPanel = new JPanel(new BorderLayout());
        runPanel.setBorder(new TitledBorder("Uruchamianie"));
        startButton = new JButton("Start");
        statusInfoLabel = new JLabel("Status:");
        runPanel.add(startButton, BorderLayout.NORTH);
        runPanel.add(statusInfoLabel,BorderLayout.CENTER);

        testingPanel.add(checkBoxPanel);
        testingPanel.add(dataPanel);
        testingPanel.add(runPanel);
        this.addActionListener();
    }

    public void addActionListener()
    {
        startButton.addActionListener(e -> testingPanelController.buttonPressed());
    }
   // public boolean getGenerateGraphButtonsSelect()
   // {
     //   return genereateGraphButton.isSelected();
   // }
    public boolean getBfsButtonSelect()
    {
        return bfsButton.isSelected();
    }
    public boolean getDijkstraButtonSelect()
    {
        return dijkstraButton.isSelected();
    }
    public int getNValue()
    {
        return Integer.parseInt(nValue.getText());
    }
    public int getMValue()
    {
        return Integer.parseInt(mValue.getText());
    }
    public int getVertexCountValue()
    {
        return Integer.parseInt(startVertexValue.getText());
    }
    public int getIncrementValue()
    {
        return Integer.parseInt(incrementValue.getText());
    }
    public int getTrailsNumber()
    {
        return Integer.parseInt(trialsNumber.getText());
    }
    public int getVertexSelection()
    {
        return pointsChoosingModeList.getSelectedIndex();
    }
    public void setStatus(String status)
    {
        statusInfoLabel.setText("Status: "+ status);
    }
    public JPanel getPanel()
    {
        return testingPanel;
    }
    public void setIteration(String iteration)
    {
        statusInfoLabel.setText("Iteracja: " + iteration);
        statusInfoLabel.revalidate();
    }

}
