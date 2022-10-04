package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private char _symbol;
    private int _XCoordinate;
    private int _YCoordinate;

    public Buoy(int XCoordinate, int YCoordinate) {
        _symbol = '#';
        _XCoordinate = XCoordinate;
        _YCoordinate = YCoordinate;
    }
}
