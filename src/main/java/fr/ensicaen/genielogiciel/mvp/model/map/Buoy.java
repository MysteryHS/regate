package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private char _symbol; // FIXME en final
    private int _XCoordinate; // FIXME respecter la casse des attributs
    private int _YCoordinate;

    public int getXCoordinate() {
        return _XCoordinate;
    }

    public int getYCoordinate() {
        return _YCoordinate;
    }

    public Buoy(int XCoordinate, int YCoordinate) { // FIXME respecter la casse des variables
        _symbol = '#';
        _XCoordinate = XCoordinate;
        _YCoordinate = YCoordinate;
    }

    public void displayBuoy(){
        System.out.println(_XCoordinate);
        System.out.println(_YCoordinate);
    }

    public int getX() {return _XCoordinate;} // FIXME quelle est la différence avec getXCoordinate() ?

    public int getY() {return _YCoordinate;}

}
