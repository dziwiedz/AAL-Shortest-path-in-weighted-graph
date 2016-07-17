package View;
import Controller.Controller;
import Model.Model;
import Controller.AlgorithmsPanelController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Gulgulgulgul on 26.05.2016.
 */
public class AlgorithmsPanel {

    private final Model model;
    private final AlgorithmsPanelController algorithmsPanelController;
    private JPanel algorithmsPanel;

    private JPanel comboBoxContainerPanel;
    private JPanel algorithmChoice;
    private JComboBox algorithmList;

    private JPanel shortestPathPanel;
    private JComboBox workMode;

    private JPanel dataPanel;
    private JPanel labels;
    private JPanel fields;
    private JTextField nSize;
    private JTextField mSize;
    private JTextField vertexCount;

    private JPanel buttonPanel;
    private JButton runButton;
    private JButton clearPathButton;
    private JCheckBox randomPointsButton;

    private JScrollPane scrollStatusPanel;
    private JPanel statusPanel;



    public AlgorithmsPanel(Model model)
    {
        algorithmsPanel = new JPanel();
        this.model = model;
        algorithmsPanelController = new AlgorithmsPanelController(model, this);

        algorithmsPanel.setLayout(new BoxLayout(algorithmsPanel, BoxLayout.Y_AXIS));
        algorithmsPanel.setBorder(new TitledBorder("Algorytmy"));
        algorithmsPanel.setPreferredSize(new Dimension(300,600));

        comboBoxContainerPanel = new JPanel(new GridLayout(0,1,1,1));

        algorithmChoice = new JPanel(new BorderLayout(2,2));
        algorithmChoice.setBackground(Color.WHITE);
        algorithmList = new JComboBox();
        algorithmList.setBounds(300, 50, 100,50);
        algorithmList.addItem("Tworzenie Grafu");
        algorithmList.addItem("BFS");
        algorithmList.addItem("Dijkstra");
        algorithmChoice.add(new JLabel("Algorytm:"),BorderLayout.NORTH);
        algorithmChoice.add(algorithmList,BorderLayout.CENTER);
        comboBoxContainerPanel.add(algorithmChoice, BorderLayout.NORTH);

        shortestPathPanel = new JPanel(new GridLayout(2,2));
        shortestPathPanel.setBackground(Color.WHITE);
        workMode = new JComboBox();
        workMode.setBounds(300, 50, 100,50);
        workMode.addItem("Zwykly");
        workMode.addItem("Zapis wyniku");
        shortestPathPanel.add(new JLabel("Tryb Pracy:"),BorderLayout.NORTH);
        shortestPathPanel.add(workMode,BorderLayout.CENTER);
        comboBoxContainerPanel.add(shortestPathPanel,BorderLayout.CENTER);
        algorithmsPanel.add(comboBoxContainerPanel);

        dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBorder(new TitledBorder("Dane:"));
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setPreferredSize(new Dimension(300,200));
        labels = new JPanel(new GridLayout(0,1,1,1));
        labels.add(new JLabel("Szerokosc [N]:"));
        labels.add(new JLabel("Wysokosc [M] :"));
        labels.add(new JLabel("Ilosc Punktow:"));
        fields = new JPanel(new GridLayout(0,1,1,1));
        nSize = new JTextField();
        mSize = new JTextField();
        vertexCount = new JTextField();
        fields.add(nSize);
        fields.add(mSize);
        fields.add(vertexCount);
        dataPanel.add(labels, BorderLayout.WEST);
        dataPanel.add(fields, BorderLayout.CENTER);
        algorithmsPanel.add(dataPanel);

        buttonPanel = new JPanel(new FlowLayout());
        runButton = new JButton("Start");
        clearPathButton = new JButton("Wyczysc");
        randomPointsButton = new JCheckBox("Losuj punkty");
        buttonPanel.add(randomPointsButton);
        buttonPanel.add(runButton);
        buttonPanel.add(clearPathButton);
        algorithmsPanel.add(buttonPanel);


        statusPanel = new JPanel(new GridLayout(0,1,1,1));
        statusPanel.setBorder(new TitledBorder("Status"));
        statusPanel.setPreferredSize(new Dimension(200,200));
        algorithmsPanel.add(new JScrollPane(statusPanel));

        //addActionListener();

    }

    public void addActionListener(Controller controller)
    {
        runButton.addActionListener(e -> algorithmsPanelController.startAlgorithm());
        runButton.addActionListener(e -> controller.repaintBoard());
        clearPathButton.addActionListener(e-> algorithmsPanelController.clearPath());
        clearPathButton.addActionListener(e -> controller.repaintBoard());
    }
    public int getAlgorithmType()
    {
        return algorithmList.getSelectedIndex();
    }
    public int getWorkMode( )
    {
        return workMode.getSelectedIndex();
    }
    public int getNSize()
    {
        if (nSize.getText().equals("")) return -1;
        else return Integer.parseInt(nSize.getText());
    }
    public int getMSize()
    {
        if (mSize.getText().equals("")) return -1;
        return Integer.parseInt(mSize.getText());
    }
    public int getVertexCount()
    {
        if (vertexCount.getText().equals("")) return -1;
        return Integer.parseInt(vertexCount.getText());
    }
    public void showCommunicat(String message)
    {
        statusPanel.add(new JLabel(message));
        statusPanel.revalidate();
        statusPanel.repaint();
    }
    public void clearStatus()
    {
        statusPanel.removeAll();
    }
    public boolean getCheckBoxValue(){return randomPointsButton.isSelected();}
    public JPanel getPanel()
    {
        return algorithmsPanel;
    }

    public void panelRepaint()
    {
        algorithmsPanel.repaint();
    }



}
