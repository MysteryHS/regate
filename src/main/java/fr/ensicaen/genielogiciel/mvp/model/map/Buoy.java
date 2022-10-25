package fr.ensicaen.genielogiciel.mvp.model.map;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class Buoy {
    private final char _symbol = '#';
    private final int _xCoordinate;
    private final int _yCoordinate;
    private final int _xAxis;
    private final int _yAxis;
    private boolean _firstAxisPassed;
    private boolean _bothAxisPassed;

    public Buoy(int XCoordinate, int YCoordinate, int xAxis, int yAxis) {
        _xCoordinate = XCoordinate;
        _yCoordinate = YCoordinate;
        _xAxis = xAxis;
        _yAxis = yAxis;
        _firstAxisPassed = false;
        _bothAxisPassed = false;
    }

    public int getX() {return _xCoordinate;}

    public int getY() {return _yCoordinate;}

    public boolean isFirstAxisPassed(ShipModel ship){
        double xFirstPosition = ship.getX();
        double yFirstPosition = ship.getY();
        ship.move();
        double xSecondPosition = ship.getX();
        double ySecondPosition = ship.getY();
    }
}
