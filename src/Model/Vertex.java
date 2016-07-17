package Model;

import java.awt.*;

/**
 * Created by Gulgulgulgul on 16.04.2016.
 */
public class Vertex {
    private Point point;
    private TileType type;
    public int vertexNumber;
    public int[] edges;
    public Vertex(Point point, int no)
    {
        this.point=point;
        type = TileType.WHITE;
        vertexNumber = no;
        this.edges = new int[4];



        for (int i=0 ; i < 4; i++)
        {
            edges[i]=vertexNumber;
        }
/*
        for (int i=0; i < 4; i++)
        {
            edges[i]=vertexNumber;
        }*/
    }

    public void setAsStartPoint()
    {
        type = TileType.START_POINT;
    }
    public void removeStartPoint() { type = TileType.WHITE; }
    public void setAsEndPoint()
    {
        type = TileType.END_POINT;
    }
    public void removeEndPoint() { type = TileType.WHITE; }
    public int getEdge(EdgeDirection side) {
        return edges[side.getSideValue()];
    }
    public Point getPoint()
    {
        return point;
    }
    public int getVertexNumber() {return vertexNumber;}

    /**
     * Funkcja ktora sprawdza czy wierzcholek ma zadane wspolrzedne
     * @param x
     * @param y
     * @return
     */
    public boolean isEqual(int x, int y)
    {
        if (point.x == x && point.y == y) return true;
        else return false;
    }

    /**
     * Funkcja ktora sprawdza czy wierzcholek jest tozsamy z zadanym punktem
     * @param p
     * @return
     */
    public boolean isEqual(Point p)
    {
        if (this.point.equals(p)) return true;
        else return false;
    }
    public TileType getTileType()
    {
        return type;
    }

    /**
     * Ustawia krawedz do sasiada
     * @param side
     * @param vertexNumber
     */
    public void setEdge(EdgeDirection side, int vertexNumber)
    {
        this.edges[side.getSideValue()]=vertexNumber;
    }

    /**
     * Usuwa krawedz do sasiada
     * @param side
     */
    public void removeEdge(EdgeDirection side)
    {
        this.edges[side.getSideValue()]=vertexNumber;
    }

    /**
     * Funkcja sprawdza czy wierzcholek ma wolnych sasiadow
     * @return true jezeli jakis sasiad jest wolny, false jezeli wszyscy sa zajeci
     */
    public boolean hasFreeEdges()
    {
        for (int e:edges)
        {
            if (e==vertexNumber) return true;
        }
        return false;
    }

    /**
     * Zwraca losowa krawedz.
     * @param randomNumber
     * @return
     */
    public EdgeDirection getRandomFreeEdge(int randomNumber)
    {
        for (int i = 0; i <4; i++)
        {
            if (edges[(randomNumber+i)%4] == vertexNumber) return EdgeDirection.values()[((randomNumber+i)%4)];
        }
        return null;
    }

    public void setTile(TileType type)
    {
        this.type = type;
    }

    public void decsraseVertexNumber(int removedVertexNumber)
    {
        if (this.vertexNumber>=removedVertexNumber) this.vertexNumber -=1;
        for (int i=0;i<4;i++)
        {
            if (edges[i] == removedVertexNumber) edges[i] = vertexNumber;
            else if (edges[i] > removedVertexNumber) edges[i]--;
        }
    }


}
