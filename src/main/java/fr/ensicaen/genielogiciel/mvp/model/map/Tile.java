package fr.ensicaen.genielogiciel.mvp.model.map;

public abstract class Tile {
    protected int _X;
    protected int _Y;

    public Tile(int X, int Y) {
        _X = X;
        _Y = Y;
    }

    public abstract void displayTile();
}
