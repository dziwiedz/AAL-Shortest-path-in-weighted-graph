package View;
import Controller.EditionPanelController;
import Model.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Gulgulgulgul on 26.05.2016.
 */
public class EditionPanel {
    private final Model model;
    private final EditionPanelController editionPanelController;

    private JPanel editionPanel;

    private JPanel instructionFieldsPanel;
    private JPanel informationModesPanel;
    private JPanel buttonsPanel;

    private JButton editModeButton;
    private JButton setStartPointButton;
    private JButton setEndPointButton;

    private JTextArea instructionArea;
    private JLabel editionModeIndicator;
    private JLabel settingStartPointIndicator;
    private JLabel settingEndPointIndicator;

    public EditionPanel(Model model)
    {
        editionPanel = new JPanel();
        this.model = model;
        this.editionPanelController = new EditionPanelController(model, this);
        editionPanel.setLayout(new BorderLayout(2,2));
        editionPanel.setBorder(new TitledBorder("Tryb Edytowania"));
        editionPanel.setPreferredSize(new Dimension(300,600));
        editionPanel.setBackground(Color.WHITE);
        buttonsPanel = new JPanel(new GridLayout(0,1,1,1));
        buttonsPanel.setBorder(new TitledBorder("Panel:"));
        editModeButton = new JButton("Wlacz/Wylacz Edycje");

        setStartPointButton = new JButton("Wybierz Punkt Startowy");
        setEndPointButton = new JButton("Wybierz Punkt Koncowy");
        buttonsPanel.add(editModeButton);
        buttonsPanel.add(setStartPointButton);
        buttonsPanel.add(setEndPointButton);
        editionPanel.add(buttonsPanel,BorderLayout.NORTH);

        instructionFieldsPanel = new JPanel(new BorderLayout(2,2));
        instructionFieldsPanel.setBorder(new TitledBorder("Instrukcje"));

        informationModesPanel = new JPanel(new GridLayout(0,1,1,1));
        informationModesPanel.setBorder(new TitledBorder("Tryby:"));
        editionModeIndicator = new JLabel( "Tryb Edycji                   : Off");
        settingStartPointIndicator = new JLabel("Wyznaczanie Punktu Startowego : Off.");
        settingEndPointIndicator = new JLabel("Wyznaczanie Punktu Koncowego  : Off.");
        informationModesPanel.add(editionModeIndicator);
        informationModesPanel.add(settingStartPointIndicator);
        informationModesPanel.add(settingEndPointIndicator);

        instructionFieldsPanel.add(informationModesPanel, BorderLayout.NORTH);

        instructionArea = new JTextArea("Usuwanie/Dodawnie wierzcholkow:\n" +
                "* Wlacz tryb Edycji\n" +
                "* Kliknij na pole puste by dodac\n" +
                "* Kliknij na pole biale by usunac\n" +
                "Ustawianie Punktu Startowego\n" +
                "* Kliknij na przycisk 'Wybierz Punkt Startowy'\n" +
                "* Kliknij na biale pole \n" +
                "Analogicznie w przypadku ustawiania \n" +
                "punktu koncowego");
        instructionArea.setEditable(false);
        instructionArea.setBackground(null);

        instructionFieldsPanel.add(instructionArea);
        editionPanel.add(instructionFieldsPanel,BorderLayout.CENTER);

        addActionListener();

    }

    public void addActionListener()
    {
        editModeButton.addActionListener(e->editionPanelController.changeEditionMode());
        setStartPointButton.addActionListener(e -> editionPanelController.setStartPoint());
        setEndPointButton.addActionListener(e-> editionPanelController.setEndPoint());
    }
    public void changeEndPointInfo(String mode)
    {
        settingEndPointIndicator.setText("Wyznaczanie Punktu Koncowego  : "+mode);
    }
    public void changeStartPointInfo(String mode)
    {
        settingStartPointIndicator.setText("Wyznaczanie Punktu Startowego : "+mode);
    }
    public void changeModeInfo(String mode)
    {
        editionModeIndicator.setText("Tryb Edycji                   : "+mode);
    }
    public void refreshInformationPanel()
    {
        informationModesPanel.revalidate();
        informationModesPanel.repaint();
    }

    public JPanel getPanel()
    {
        return editionPanel;
    }

}
