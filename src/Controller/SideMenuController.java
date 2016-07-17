package Controller;

import View.SidePanel;
import Model.Model;
/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class SideMenuController implements SideMenuListener {

    private final Model model;
    private final SidePanel sidePanel;

    public SideMenuController (Model model, SidePanel sidePanel)
    {
        this.model = model;
        this.sidePanel = sidePanel;
    }
    @Override
    public void displayAlgorithmsPanel()
    {
        sidePanel.displayAlgorithmsPanel();
    }
    @Override
    public void displayEditionPanel()
    {
        sidePanel.displayEditionPanel();
    }
    @Override
    public void displayTestingPanel()
    {
        sidePanel.displayTestingPanel();
    }
    @Override
    public void displayResultPanel()
    {
        sidePanel.displayResultPanel();
    }
}
