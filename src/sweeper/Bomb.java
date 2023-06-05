package sweeper;

class Bomb
{
    private Matrix bombMap;
    private int totalBombs; // сколько бомб

    Bomb(int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start()
    {
        bombMap = new Matrix(Box.ZERO);
        // разместим все бомбы
        for (int j = 0; j < totalBombs; j ++)
            placeBomb();
    }

    Box get (Coord coord)
    {
        return bombMap.get(coord);
    }

    private void fixBombsCount()
    {
        // Контроль указания слишком большого количества бомб
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb()
    {
        // размещаем бомбы
        while (true)
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord,Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord)
    {
        // увеличит числа вокруг бомбы
        for (Coord around : Ranges.getCoordsAroynd(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs()
    {
            return totalBombs;
    }
}
