package fr.ensicaen.genielogiciel.mvp.model.map;

public class Sand extends Tile{
    public Sand(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol(){
        return '.';
    }

    public String getImageSRC() {
        return "file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/sand.png";
    }

}
