package fr.ensicaen.genielogiciel.mvp.model.map;

public class Water extends Tile {
    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public void displayTile() {
        System.out.print('~');
    }

    @Override
    public char getSymbol(){
        return '~';
    }
}
