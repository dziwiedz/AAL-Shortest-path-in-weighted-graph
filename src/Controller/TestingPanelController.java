package Controller;

import Model.Model;
import View.TestingPanel;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class TestingPanelController implements TestingPanelListener {

    private final Model model;
    private final TestingPanel testingPanel;

    public TestingPanelController (Model model, TestingPanel testingPanel)
    {
        this.model = model;
        this.testingPanel = testingPanel;
    }

    @Override
    public void buttonPressed()
    {
        int numberVerticies = testingPanel.getVertexCountValue();
        testingPanel.setStatus("W trakcie...");
        if (testingPanel.getTrailsNumber()!= 0) {

            startGenerateGraph(testingPanel.getNValue(),testingPanel.getMValue(),numberVerticies);
            for (int i = 0; i < testingPanel.getTrailsNumber(); ++i) {
                testingPanel.setIteration(Integer.toString(i));
                System.out.println("Iteracja : " + i);
                randomVerticies();
                if (testingPanel.getBfsButtonSelect()) {
                    startBFS();
                }
                if (testingPanel.getDijkstraButtonSelect()) {
                    startDijkstra();
                }
                model.startTimer();
                model.addNewVertex(testingPanel.getIncrementValue());
                model.stopTimer();
                System.out.println("Uplynelo :" + model.getTimer());
            }
            if (testingPanel.getBfsButtonSelect()) model.calculateQ("BFS");
            if (testingPanel.getDijkstraButtonSelect()) model.calculateQ("Dijkstra");
            testingPanel.setStatus("Koniec.");
            model.setSettings(10,10,0);
            model.createNewGraph();
        }
    }
    @Override
    public void startGenerateGraph(int N, int M, int vertexCount)
    {
        model.setSettings(N,M,vertexCount);
        model.clearLists();
        model.generateGraph2();
    }
    @Override
    public void startBFS()
    {
        model.setBackToWhite();
        model.startTimer();
        model.BFSShortestPath();
        model.stopTimer();
        System.out.println("Uplynelo BFS:" + model.getTimer());
        this.saveResult("BFS");
    }
    @Override
    public void startDijkstra()
    {
        model.setBackToWhite();
        model.startTimer();
        model.DijkstraAlgorithm();
        model.stopTimer();
        this.saveResult("Dijkstra");
    }
    @Override
    public void saveResult(String algorithmName)
    {
        model.addNewResult(algorithmName);
    }
    @Override
    public void randomVerticies()
    {
        if (testingPanel.getVertexSelection()==0) model.setRandomEndStartPoints();
        else if (testingPanel.getVertexSelection()==1) model.setFirstAndLastPoints();
        else model.setMaxDistanceStartEndPoints();
    }
}
