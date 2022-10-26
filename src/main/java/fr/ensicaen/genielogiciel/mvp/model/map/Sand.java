package fr.ensicaen.genielogiciel.mvp.model.map;

public class Sand extends Tile{
    public Sand(int x, int y) {
        super(x, y);
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
