package fr.ensicaen.genielogiciel.mvp.model.map;

public class Water extends Tile {
    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol(){
        return '~';
    }

    public String getSrcImage() {
        return "file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/water.png";
    }
}
