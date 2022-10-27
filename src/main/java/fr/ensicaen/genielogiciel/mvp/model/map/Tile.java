package fr.ensicaen.genielogiciel.mvp.model.map;

public abstract class Tile {
    protected int _x;
    protected int _y;

    public Tile(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public abstract char getSymbol();

    public abstract String getSrcImage();
}
