package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private char _symbol;
    private int _XCoordinate;
    private int _YCoordinate;
    private boolean _xAxis;
    private boolean _yAxis;

    public int getXCoordinate() {
        return _XCoordinate;
    }

    public int getYCoordinate() {
        return _YCoordinate;
    }

    public Buoy(int XCoordinate, int YCoordinate) {
        _symbol = '#';
        _XCoordinate = XCoordinate;
        _YCoordinate = YCoordinate;
    }

    public void displayBuoy(){
        System.out.println(_XCoordinate);
        System.out.println(_YCoordinate);
    }

    public int getX() {return _XCoordinate;}

    public int getY() {return _YCoordinate;}

}
