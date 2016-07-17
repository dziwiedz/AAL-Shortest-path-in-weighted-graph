package Controller;
import Model.*;
import View.AlgorithmsPanel;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class AlgorithmsPanelController implements AlgorithmsPanelListener {
    private final AlgorithmsPanel algorithmsPanel;
    private final Model model;
    public AlgorithmsPanelController( Model model,AlgorithmsPanel algorithmsPanel)
    {
        this.algorithmsPanel = algorithmsPanel;
        this.model = model;
    }
    @Override
    public void startAlgorithm()
    {
        algorithmsPanel.clearStatus();
        if(checkData())
        {
            switch (algorithmsPanel.getAlgorithmType())
            {
                case 0:
                {
                    model.setSettings(algorithmsPanel.getNSize(),algorithmsPanel.getMSize(),algorithmsPanel.getVertexCount());
                    if (algorithmsPanel.getWorkMode()==1) model.startTimer();
                    model.createNewGraph();
                    if (algorithmsPanel.getWorkMode()==1)
                    {
                        model.stopTimer();
                        model.addNewResult("GRAF");
                    }
                    if (algorithmsPanel.getCheckBoxValue()) model.setRandomEndStartPoints();
                    break;
                }
                case 1:
                {
                    model.setBackToWhite();
                    if (algorithmsPanel.getCheckBoxValue()) model.setRandomEndStartPoints();
                    if (algorithmsPanel.getWorkMode()==1) model.startTimer();
                    int[] p = model.BFSShortestPath();
                    if (algorithmsPanel.getWorkMode()==1)
                    {
                        model.stopTimer();
                        model.addNewResult("BFS");
                    }
                    model.setShortestPath(p,model.getStartPoint(),model.getEndPoint());
                    break;
                }
                case 2:
                {
                    model.setBackToWhite();
                    if (algorithmsPanel.getCheckBoxValue()) model.setRandomEndStartPoints();
                    if (algorithmsPanel.getWorkMode()==1) model.startTimer();
                    int[]p = model.DijkstraAlgorithm();
                    if (algorithmsPanel.getWorkMode()==1)
                    {
                        model.stopTimer();
                        model.addNewResult("DIJK");

                    }
                    model.setShortestPath(p,model.getStartPoint(),model.getEndPoint());
                    break;
                }
            }
            algorithmsPanel.panelRepaint();
        }
    }
    @Override
    public void clearPath()
    {
        model.setBackToWhite();
    }

    public boolean checkData()
    {
        boolean correct = true;
        if(algorithmsPanel.getNSize() <= 0)
        {
            algorithmsPanel.showCommunicat("BLAD:Szerokosc ujemna lub rowna 0.");
            correct = false;
        }
        if (algorithmsPanel.getMSize()<=0)
        {
            algorithmsPanel.showCommunicat("BLAD:Wysokosc ujemna lub rowna 0.");
            correct = false;
        }
        if(algorithmsPanel.getVertexCount()<0)
        {
            algorithmsPanel.showCommunicat("BLAD:Ilosc wierzcholkow nie moze byc ujemna");
            correct = false;
        }
        return correct;
    }

}
