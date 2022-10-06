package fr.ensicaen.genielogiciel.mvp.model.map;

public class Sand extends Tile{
    public Sand(int X, int Y) {
        super(X, Y);
    }

    @Override
    public void displayTile() {
        System.out.print('.');
    }

    @Override
    public char getSymbol(){
        return '.';
    }
}
