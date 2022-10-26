package fr.ensicaen.genielogiciel.mvp.model.map;

public class Water extends Tile {
    public Water(int X, int Y) {
        super(X, Y);
    }

    @Override
    public char getSymbol(){
        return '~';
    }
}
