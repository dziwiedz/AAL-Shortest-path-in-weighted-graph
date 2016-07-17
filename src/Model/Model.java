package Model;

import java.awt.*;
import java.time.Clock;
import java.util.*;



/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
public class Model {
    /**
     * Maksymalna ilosc wierzcholkow
     */
    private int MAX_WHITE_VERTEX;
    /** Szerokosc*/
    private int N;
    /** Wysokosc */
    private int M;

    private ResultTable resultTable;

    private Vector<Vertex> vertexList;
    LinkedList<Integer> availablePointsList;

    private Random generator;

    private Timer timer;

    ArrayList<String[]> results;
    ArrayList<String[]> testingResults;

    private boolean editingMode;
    private boolean settingStartPoint;
    private boolean settingEndPoint;

    /**
     * KONSTRUKTOR
     */
    public Model()
    {
        N = 0;
        M = 0;
        MAX_WHITE_VERTEX = 0;

        generator = new Random();
        vertexList = new Vector<>();
        availablePointsList = new LinkedList<>();

        editingMode = false;
        settingEndPoint = false;
        settingStartPoint = false;

        results = new ArrayList<String[]>();
        testingResults = new ArrayList<String[]>();

        resultTable = new ResultTable(results);
        timer = new Timer();


    }

    public void setSettingStartPoint(boolean value)
    {
        settingStartPoint = value;
    }
    public boolean getSettingStartPoint() {return settingStartPoint;}
    public void setSettingEndPoint (boolean value) { settingEndPoint = value;}
    public boolean getSettingEndPoint() { return settingEndPoint;}
    public void changeMode()
    {
        editingMode = !editingMode;
    }

    public boolean getMode()
    {
        return editingMode;
    }
    /**
     * Funkcja ustawiajaca parametry rastra
     * @param N Szerokosc
     * @param M Wysokosc
     * @param MAX Maksymalna ilosc wierzcholkow
     */
    public void setSettings(int N, int M, int MAX)
    {
        this.N = N;
        this.M = M;
        this.MAX_WHITE_VERTEX = MAX;
    }
    public void startTimer() {timer.startTimer();}
    public void stopTimer() {timer.stopTimer();}
    public double getTimer()
    {
        return timer.getTime();
    }

    public int getN()
    {
        return N;
    }
    public int getM()
    {
        return M;
    }
    public int getVertexListSize() { return vertexList.size();}
    public Point getVertexPoint(int vertexNumber)
    {
        return vertexList.elementAt(vertexNumber).getPoint();
    }
    public ResultTable getResultTable() { return resultTable; }

    /**
     *
     * @return numer wierzcholka startowego
     */
    public int getStartPoint()
    {
        for (int i=0 ; i < vertexList.size(); i++)
        {
            if (vertexList.elementAt(i).getTileType() == TileType.START_POINT)
                return vertexList.elementAt(i).getVertexNumber();
        }
        return 0;
    }

    /**
     *
     * @return numer wierzcholka
     */
    public int getEndPoint()
    {
        for (int i=0 ; i < vertexList.size(); i++)
        {
            if (vertexList.elementAt(i).getTileType() == TileType.END_POINT)
                return vertexList.elementAt(i).getVertexNumber();
        }
        return vertexList.lastElement().getVertexNumber();
    }

    /**
     *
     * @param p punkt
     */
    public void setAsStartPoint(Point p)
    {
        p.x = (int) p.getX()/10;
        p.y = (int) p.getY()/10;
        int vertexIndex;
        int vertexNumber = getVertexNumber(p);
        if (vertexNumber!=-1)
        {
            vertexIndex = vertexNumber;
            if (vertexList.elementAt(vertexIndex).getTileType()!=TileType.START_POINT)
            {
                checkAndSetToWhite(TileType.START_POINT);
                vertexList.elementAt(vertexIndex).setAsStartPoint();
            }
            else vertexList.elementAt(vertexIndex).removeStartPoint();
        }
    }

    /**
     *
     * @param p punkt
     */
    public void setAsEndPoint(Point p)
    {
        p.x = (int) p.getX()/10;
        p.y = (int) p.getY()/10;
        int vertexIndex;
        int vertexNumber = getVertexNumber(p);
        if (vertexNumber!=-1)
        {
            vertexIndex = vertexNumber;
            if (vertexList.elementAt(vertexIndex).getTileType()!=TileType.END_POINT)
            {
                checkAndSetToWhite(TileType.END_POINT);
                vertexList.elementAt(vertexIndex).setAsEndPoint();
            }
            else vertexList.elementAt(vertexIndex).removeEndPoint();
        }
    }

    /**
     * Losuje punkt startowy i koncowy
     */
    public void setRandomEndStartPoints()
    {
        checkAndSetToWhite(TileType.START_POINT);
        checkAndSetToWhite(TileType.END_POINT);
        int startPoint = generator.nextInt(vertexList.size());
        int endPoint = generator.nextInt(vertexList.size());
        while(startPoint==endPoint) endPoint = generator.nextInt(vertexList.size());
        vertexList.elementAt(startPoint).setAsStartPoint();
        vertexList.elementAt(endPoint).setAsEndPoint();
    }

    /**
     * Ustawia punkt startowy i koncowy jako pierwszy wierzcholek i ostatni z listy VertexList
     */
    public void setFirstAndLastPoints()
    {
        checkAndSetToWhite(TileType.START_POINT);
        checkAndSetToWhite(TileType.END_POINT);
        vertexList.elementAt(0).setAsStartPoint();
        vertexList.lastElement().setAsEndPoint();
    }

    /**
     * Ustawia punkty startowy i koncowy jako punkty z najwieksza odlegloscia od siebie po wspolrzednych
     */
    public void setMaxDistanceStartEndPoints()
    {
        int distance =0;
        int maxDistance = 0;
        int maxIndeks = 0;

        for (int i = 0 ; i < vertexList.size(); i++)
        {
            Point p = vertexList.get(i).getPoint();
            distance = p.x+p.y;
            if (distance>maxDistance)
            {
                maxDistance = distance;
                maxIndeks = i;
            }
        }
        checkAndSetToWhite(TileType.START_POINT);
        checkAndSetToWhite(TileType.END_POINT);
        vertexList.get(0).setAsStartPoint();
        vertexList.get(maxIndeks).setAsEndPoint();
    }

    /**
     *
     * @param type Typ wierzcholka, ktory ma zostac zamieniony
     */
    private void checkAndSetToWhite(TileType type)
    {
        for (int i=0;i< vertexList.size();i++)
        {
            if (vertexList.elementAt(i).getTileType()==type)
            {
                vertexList.elementAt(i).setTile(TileType.WHITE);
                break;
            }
        }
    }
    /**
     * Funkcja zwracajaca typ wierzcholka
     * @param x wspolrzedna X
     * @param y Wspolrzedna Y
     * @return TileType
     */
    public TileType getTile(int x, int y)
    {
        for (Vertex v : vertexList)
        {
            if (v.isEqual(x,y)) return v.getTileType();
        }
        return null;
    }

    /**
     * Funkcja zwrcajaca numer wierzcholka o zadanym punkcie.
     * @param point Szukany wierzcholek ma wspolrzedne point
     * @return VertexNumber lub -1 w przypadku gdy nie istnieje
     */
    private int getVertexNumber(Point point)
    {
        for (Vertex v : vertexList)
        {
            if (v.isEqual(point)) return v.getVertexNumber();
        }
        return -1;
    }

    public void addNewVertex(int vertexToAdd)
    {
        this.MAX_WHITE_VERTEX += vertexToAdd;
        generateGraph2();
    }

    public void createNewGraph()
    {
        clearLists();
        generateGraph2();
    }
    public void clearLists()
    {
        vertexList.clear();
        availablePointsList.clear();
    }
    /**
     * Tworzy graf. Tworzy liste numerow wierzcholkow ktore maja jeszcze sasiada.
     */
    public void generateGraph2()
    {
        if (MAX_WHITE_VERTEX<1) return;
        int randomElement;
        int vertexAdded;
        EdgeDirection side;
        //vertexList.clear();
        if (vertexList.isEmpty())
        {
            vertexList.add(new Vertex(new Point(0,0),0));
            availablePointsList.add(0);
        }

        while (vertexList.size() < MAX_WHITE_VERTEX)
        {
            while(true)
            {

                randomElement = generator.nextInt(availablePointsList.size());
                if (vertexList.elementAt(availablePointsList.get(randomElement)).hasFreeEdges())
                {
                    side = vertexList.elementAt(availablePointsList.get(randomElement)).getRandomFreeEdge(generator.nextInt(256));
                    vertexAdded = addVertexInDirection(availablePointsList.get(randomElement), side);
                    for (int j =1 ; j <= vertexAdded ; j++)
                    {
                        availablePointsList.add(vertexList.size()-j);
                    }
                    break;
                }
                else
                {
                    availablePointsList.remove(randomElement);
                }
            }
        }
    }

    public boolean checkPoint (Point p)
    {
        int x = (int) p.getX()/10, y = (int) p.getY()/10;
        if (x>=0 && x < N && y >=0 && y < M) return true;
        else return false;
    }
    /**
     * Dla punktow x,y sprawdza czy punkt jest wolny.
     * @param x Wspolrzedna X
     * @param y Wspolrzedna Y
     * @return true - jezeli punkt jest wolny, false - w przypadku gdy zajety
     */
    private boolean checkNeighbour(int x, int y)
    {
        if (x>=0 && x < N && y >=0 && y < M)
        {
            if (getTile(x,y)==null) return true;
        }
        return false;
    }

    /**
     * @todo Dodawanie punktow -> sprawdzenie jaki numer wierzcholka jest wolny
     * Dodaje punkt do grafu o zadanych wspolrzednych
     * @param p Punkt ktory zostanie dodany
     */
    public void addPoint(Point p)
    {
        p.x = (int) p.getX()/10;
        p.y = (int) p.getY()/10;
        vertexList.add(new Vertex(p,MAX_WHITE_VERTEX));
        setNeigbours(MAX_WHITE_VERTEX);
        MAX_WHITE_VERTEX++;
    }

    /**
     * @todo Zrobic
     * @param p usuniecie punktu o wspolrzednych
     */
    public void removePoint(Point p)
    {
        p.x = (int) p.getX()/10;
        p.y = (int) p.getY()/10;
        int vertexToRemove = getVertexNumber(p);
        for (int i=0;i<vertexList.size();++i)
        {
            vertexList.elementAt(i).decsraseVertexNumber(vertexToRemove);
        }
        vertexList.remove(vertexToRemove);
    }


    /**
     * Dodaje losowa ilosc wierzcholkow w zadanym kierunku
     * @param vertexNumber wierzcholek startowy
     * @param side kierunek dodawania
     * @return ilosc wierzcholkow dodanych
     */
    public int addVertexInDirection(int vertexNumber, EdgeDirection side)
    {
        int currentVertexNumber = vertexNumber;
        Point newPoint;
        int vertexAdded = 0;
        int vertexToAdd;

        vertexToAdd = generator.nextInt(MAX_WHITE_VERTEX-vertexList.size());
        if (vertexToAdd==0) vertexToAdd = 1;
        vertexToAdd= vertexToAdd;

        for (int i = 0; i < vertexToAdd; ++i)
        {
            newPoint = getNextPointInDirectionOfVertex(currentVertexNumber, side);
            if (newPoint!=null && checkNeighbour((int)newPoint.getX(),(int)newPoint.getY()))
            {
                currentVertexNumber = vertexList.size();
                vertexList.add(new Vertex(newPoint, vertexList.size()));
                setNeigbours(currentVertexNumber);
                vertexAdded++;

            }
            else return vertexAdded;

        }

        return vertexAdded;
    }

    /**
     * Zwraca punkt nastepny w zadanym kierunku.
     * Jezeli punkt przekracza rozmiary rastra, zwraca punkt (-1,-1)
     * @param vertexNumber Numer Wierzcholka
     * @param side Kierunek
     * @return Point
     */
    public Point getNextPointInDirectionOfVertex(int vertexNumber, EdgeDirection side)
    {
        Point point = vertexList.elementAt(vertexNumber).getPoint();
        switch(side)
        {
            case LEFT:
            {
                if (point.x>0)
                {
                    return new Point((int)point.getX() - 1, (int)point.getY());
                }
                break;
            }
            case RIGHT:
            {
                if (point.x+1<N)
                {
                    return new Point((int)point.getX() +1, (int)point.getY());
                }
                break;
            }
            case UP:
            {
             if (point.y+1<M)
                {
                 return new Point((int)point.getX() , (int)point.getY() + 1);
             }
                break;
            }
            case DOWN:
            {
                if (point.y>0)
                {
                    return new Point((int)point.getX() , (int)point.getY() - 1);
                }
                break;
            }
        }
        return null; //new Point(-1,-1);
    }

    /**
     * Ustawia sasiadow dla wierzcholka
     * @param vertexNumber Numer wierzcholka dla ktorego powinien zostac ustawiony sasiad
     */
    public void setNeigbours(int vertexNumber)
    {
        Point point;
        int neighbourVertexNumber;
        for (EdgeDirection side: EdgeDirection.values())
        {
            point = getNextPointInDirectionOfVertex(vertexNumber, side);
            if (point!= null)
            {
                neighbourVertexNumber = getVertexNumber(point);
                if (neighbourVertexNumber!=-1)
                {
                    vertexList.elementAt(vertexNumber).setEdge(side, neighbourVertexNumber);
                    vertexList.elementAt(neighbourVertexNumber).setEdge(side.getOppositeSide(side),vertexNumber);
                }
            }
        }
    }

    /**
     * Algorytm BFS do wyszukiwania najkrotszej sciezki pomiedzy zadanymi wierzcholkami
     */
    public int[] BFSShortestPath()
    {
        int startVertexNumber = getStartPoint();
        int endVertexNumber = getEndPoint();
        boolean visited[] = new boolean[vertexList.size()];
        int P[] = new int[vertexList.size()];

        for (int i=0 ;i<vertexList.size();++i)
        {
            visited[i]=false;
            P[i] = -2;
        }
        LinkedList<Integer> Q = new LinkedList<>();

        int currentVertexNumber, u=-1;
        P[startVertexNumber] = -1;
        Q.push(startVertexNumber);
        visited[startVertexNumber]=true;
        while(!Q.isEmpty())
        {
            currentVertexNumber = Q.pollLast();
            if (currentVertexNumber == endVertexNumber) break;
            else
            {
                for (EdgeDirection side: EdgeDirection.values())
                {
                    u = vertexList.elementAt(currentVertexNumber).getEdge(side);
                    if (u != currentVertexNumber) {
                        if (!visited[u]) {
                            P[u] = currentVertexNumber;
                            Q.push(u);
                            visited[u] = true;
                        }
                    }

                }
            }
        }

//        setShortestPath(P, startVertexNumber,endVertexNumber);
        return P;

    }

    /**
     * Algorytm Dijkstry do wyszukiwania najkrotszej sciezki pomiedzy dwoma wierzcholkami.

     */
    public int[] DijkstraAlgorithm()
    {
        int startVertexNumber = getStartPoint();
        int endVertexNumber = getEndPoint();

        int d[] = new int[vertexList.size()];
        int p[] = new int[vertexList.size()];

        int minDValue, currentVertexNumber, minDValueIndeks, w;

        Vector<Integer> S = new Vector<>();
        Vector<Integer> Q = new Vector<>();

        for (int i =0; i <vertexList.size();i++)
        {
            Q.add(vertexList.elementAt(i).getVertexNumber());
            d[i]=Integer.MAX_VALUE;
            p[i]=-1;

        }

        d[startVertexNumber]=0;

        minDValueIndeks = startVertexNumber;

        while(!Q.isEmpty())
        {
            minDValue = Integer.MAX_VALUE;
            for (int i=0; i <Q.size();i++)
            {
                if (minDValue > d[Q.elementAt(i)])
                {
                    minDValue = d[Q.elementAt(i)];
                    minDValueIndeks = i;
                }
            }

            if(Q.size()<=minDValueIndeks)
            {
                minDValueIndeks=0;
            }

            currentVertexNumber = Q.get(minDValueIndeks);
            S.add(Q.get(minDValueIndeks));
            Q.remove(minDValueIndeks);

            for (EdgeDirection side : EdgeDirection.values())
            {
                w=vertexList.elementAt(currentVertexNumber).getEdge(side);
                for( int i =0 ; i < Q.size(); i++)
                {
                    if(Q.elementAt(i)!=w && w !=-1)
                    {
                        if(d[w] > d[currentVertexNumber]+1)
                        {
                            d[w] = d[currentVertexNumber] + 1;
                            p[w] = currentVertexNumber;
                        }
                    }
                }
            }
        }
//        setShortestPath(p, startVertexNumber,endVertexNumber);
        return p;
    }



    /**
     * Ustawia wszystkie wierzcholki jako biale
     */
    public void setBackToWhite()
    {
        for (int i =0; i < vertexList.size();i++)
        {
            if (vertexList.elementAt(i).getTileType()==TileType.SHORTESTPATH) vertexList.elementAt(i).setTile(TileType.WHITE);
        }
    }

    /**
     * Utawia najkrotsza sciezke
     * @param P tablica z punktami sciezki
     * @param startVertexNumber wierzcholek startowy
     * @param endVertexNumber wierzcholek koncowy
     */
    public void setShortestPath(int[] P, int startVertexNumber, int endVertexNumber)
    {
        int currentVertexNumber = endVertexNumber;
        while(true)
        {
            if(currentVertexNumber != startVertexNumber) {
                currentVertexNumber = P[currentVertexNumber];
                vertexList.elementAt(currentVertexNumber).setTile(TileType.SHORTESTPATH);
            }
            else
            {
                vertexList.elementAt(currentVertexNumber).setTile(TileType.START_POINT);
                break;
            }
        }
    }

    /**
     * Dodaje wynik do tabeli
     * @param algorithmName nazwa algorytmu
     */
    public void addNewResult(String algorithmName)
    {
        String[] result = new String[6];
        result[0] = algorithmName;
        result[1] = Integer.toString(N);
        result[2] = Integer.toString(M);
        result[3] = Integer.toString(vertexList.size());
        result[4] = Double.toString(timer.getTime());
        result[5]= "";
        this.results.add(result);

    }


    public void clearTable()
    {
        this.results.clear();
    }

    /**
     * Oblicza q(n) dla zadanego algorytmu
     */
    public void calculateQ(String algorithmName)
    {
        double q;
        int mediana =0;

        for (int j = 0; j < results.size() ; j++)
        {
            if (results.get(j)[0] == algorithmName) mediana++;
        }
        mediana = (mediana+1)/2;
        int index = getMedianaIndex(mediana,algorithmName);
        int n = Integer.parseInt(results.get(index)[3]);
        Double t = Double.parseDouble( results.get(index)[4]);
        double TN = calculateTN(n,t,algorithmName);


        double c = TN/t;
        for (String[] result : results)
        {
            if (result[0]==algorithmName)
            {
                n = Integer.parseInt(result[3]);
                t = Double.parseDouble(result[4]);
                TN= calculateTN(n,t,algorithmName);
                q = c * (t/TN);
                result[5] = Double.toString(q);
            }
        }

    }

    /**
     *
     * @param mediana
     * @param name
     * @return indeks srodkowoego wyniku
     */
    private int getMedianaIndex(int mediana, String name)
    {
        int index = 0;
        for (int i = 0 ; i  < results.size() ; i++)
        {

            if (results.get(i)[0]==name) mediana--;
            if (mediana==0) return i;

        }

        return 1;
    }

    /**
     * Oblicza wartosc teoretyczna
     * @param n
     * @param t
     * @param algorithmName
     * @return
     */
    private double calculateTN(int n, double t, String algorithmName)
    {
        double TN;
        if (algorithmName == "BFS") TN = 5*n;
        else if(algorithmName =="DIJK") TN = 4*n * Math.log(n);
        else TN = Math.pow(n,2);
        return TN;

    }

    /**
     * Dodaje wynik
     * @param name
     */
    public void addNewResults(String name)
    {
        String[] result = new String[6];
        result[0] = name;
        result[1] = Integer.toString(N);
        result[2] = Integer.toString(M);
        result[3] = Integer.toString(vertexList.size());
        result[4] = Double.toString(timer.getTime());
        result[5] = "";


    }


}
