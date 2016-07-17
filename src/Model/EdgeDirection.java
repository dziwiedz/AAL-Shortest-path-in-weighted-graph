package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gulgulgulgul on 24.05.2016.
 */
public enum EdgeDirection {
    LEFT(0), RIGHT(1), UP(2), DOWN(3) ;

    private int sideValue;
    private static Map<Integer, EdgeDirection> map = new HashMap<Integer, EdgeDirection>();
    static{
        for (EdgeDirection sideEnum : EdgeDirection.values())
        {
            map.put(sideEnum.sideValue, sideEnum);
        }
    }

    private EdgeDirection(final int side)
    { sideValue = side ;}
    public static EdgeDirection valueOf(int sideValue)
    {
        return map.get(sideValue);
    }

    public EdgeDirection getOppositeSide(EdgeDirection side)
    {
        switch (side)
        {
            case LEFT:
            {
                return RIGHT;
            }
            case RIGHT:
            {
                return LEFT;
            }
            case UP:
            {
                return DOWN;
            }
            case DOWN:
            {
                return UP;
            }
        }
        return null;
    }
    public int getSideValue() { return sideValue;}
}
