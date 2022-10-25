package fr.ensicaen.genielogiciel.mvp.model.map;

public abstract class Tile {
    protected int _X; // FIXME respecter la casse des attributs
    protected int _Y;

    public Tile(int X, int Y) {
        _X = X;
        _Y = Y;
    }

    public int getCoordinateX() {
        return _X;
    }

    public int getCoordinateY() {
        return _Y;
    }

    public abstract void displayTile();

    public abstract char getSymbol();
}
