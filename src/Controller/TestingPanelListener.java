package Controller;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public interface TestingPanelListener {
    public void buttonPressed();
    public void startGenerateGraph(int N, int M, int vertexCount);
    public void startBFS();
    public void startDijkstra();
    public void randomVerticies();
    public void saveResult(String algorithmName);
}
