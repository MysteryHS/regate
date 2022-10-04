package fr.ensicaen.genielogiciel.mvp.model.map;

public class Sand {
    private char _symbol;
    private int _XCoordinate;
    private int _YCoordinate;

    public Sand(int XCoordinate, int YCoordinate) {
        _symbol = '.';
        _XCoordinate = XCoordinate;
        _YCoordinate = YCoordinate;
    }
}
