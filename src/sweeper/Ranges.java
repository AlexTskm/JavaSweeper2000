package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coord size; // Размеры экрана
    private static ArrayList<Coord> allCoords; // Список хранящий все координаты
    private static Random random = new Random(); // Случайные координаты

    public static void setSize(Coord _size)
    {
        size = _size;
        allCoords = new ArrayList<Coord>();
        for ( int y = 0; y < size.y; y ++)
            for ( int x = 0; x < size.x; x ++)
                allCoords.add(new Coord(x, y));
    }
    public static Coord getSize()
    {
        return size;
    }

    public static ArrayList<Coord> getAllCoords()
    {
        return allCoords;
    }

    static boolean inRange(Coord coord)
    {
        return coord.x >= 0 && coord.x < size.x &&
               coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord()
    {
        return new Coord( random.nextInt(size.x),
                          random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAroynd(Coord coord)
    {
        // Перебирает клетки вокруг клетки
        Coord aroynd;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.x-1; x <= coord.x+1; x++)
            for (int y = coord.y-1; y <= coord.y+1; y++)
                if (inRange(aroynd = new Coord(x, y)))
                    if (!aroynd.equals(coord))
                        list.add(aroynd);
        return list;
    }
}
