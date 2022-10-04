package fr.ensicaen.genielogiciel.mvp.model.map;

public class Water {
    private final char _symbol;
    private int _XCoordinate;
    private int _YCoordinate;

    public Water(int XCoordinate, int YCoordinate) {
        _symbol = '~';
        _XCoordinate = XCoordinate;
        _YCoordinate = YCoordinate;
    }
}
