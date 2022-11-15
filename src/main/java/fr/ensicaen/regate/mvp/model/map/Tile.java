package fr.ensicaen.regate.mvp.model.map;

public abstract class Tile {
    private final int _x;
    private final int _y;

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

    public abstract String getImageSRC();
}
