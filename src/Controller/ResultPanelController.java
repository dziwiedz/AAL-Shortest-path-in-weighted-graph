package Controller;
import Model.*;


import View.ResultPanel;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class ResultPanelController implements ResultPanelListener {

    ResultPanel resultPanel;
    Model model;

    public ResultPanelController(Model model, ResultPanel resultPanel)
    {
        this.resultPanel = resultPanel;
        this.model = model;
    }

    @Override
    public void repaintTable()
    {

    }
    @Override
    public void clearResults()
    {
        model.clearTable();
        repaintTable();
    }
    @Override
    public void refresh()
    {
        resultPanel.revalidateTable();
    }
}
