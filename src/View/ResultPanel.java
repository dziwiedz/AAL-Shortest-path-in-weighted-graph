package View;
import Controller.ResultPanelController;
import Model.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by Gulgulgulgul on 26.05.2016.
 */
public class ResultPanel {

    private JPanel resultPanel;
    private final Model model;
    private final ResultTable resultTable;
    private final ResultPanelController resultPanelController;

    private JPanel headerPanel;
    private JPanel resultTablePanel;
    private JPanel panel;

    private JTable jTable;

    private JScrollPane scrollPanel;

    private JButton clearButton;

    public ResultPanel(Model model)
    {
        resultPanel = new JPanel();
        this.model = model;
        this.resultPanelController = new ResultPanelController(model,this);
        resultPanel.setLayout(new BorderLayout(2,2));
        resultPanel.setBorder(new TitledBorder("Asymptotyka"));
        resultPanel.setPreferredSize(new Dimension(300,600));

        headerPanel = new JPanel(new FlowLayout());
        clearButton = new JButton("Wyczysc Wyniki");
        headerPanel.add(clearButton);

        resultTablePanel = new JPanel(new BorderLayout());
        resultTablePanel.setBorder(new TitledBorder("Tabela wynikowa"));

        resultTable = model.getResultTable();

        jTable = new JTable(resultTable);
        jTable.setFillsViewportHeight(true);
        //
       // jTable.setAutoResizeMode(JTable.);
        //jTable.setPreferredSize(new Dimension(200,200));
        scrollPanel = new JScrollPane(jTable);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resultTablePanel.add(scrollPanel, BorderLayout.CENTER);
        //resultTablePanel.setPreferredSize(new Dimension(100,200));
      //  resultTablePanel.setPreferredSize((new Dimension(280,500)));
        panel = new JPanel(new BorderLayout());

        panel.add(resultTablePanel);

        resultPanel.add(headerPanel,BorderLayout.NORTH);
        resultPanel.add(panel,BorderLayout.CENTER);

    }

    public void addActionListener()
    {

        clearButton.addActionListener(e -> resultPanelController.clearResults());
    }
    public void revalidateTable()
    {
        scrollPanel.revalidate();
    }

    public JPanel getPanel()
    {
        return resultPanel;
    }
}
