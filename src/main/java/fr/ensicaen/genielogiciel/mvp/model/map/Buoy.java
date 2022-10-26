package fr.ensicaen.genielogiciel.mvp.model.map;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class Buoy {
    private final char _symbol = '#';
    private final int _xCoordinate;
    private final int _yCoordinate;
    private final int _firstAxis;
    private final int _secondAxis;
    private boolean _firstAxisPassed;
    private boolean _bothAxisPassed;

    public Buoy(int XCoordinate, int YCoordinate, int xAxis, int yAxis) {
        _xCoordinate = XCoordinate;
        _yCoordinate = YCoordinate;
        _firstAxis = xAxis;
        _secondAxis = yAxis;
        _firstAxisPassed = false;
        _bothAxisPassed = false;
    }

    public int getX() {return _xCoordinate;}

    public int getY() {return _yCoordinate;}

    public boolean isAxisPassed(ShipModel ship){
        double xFirstPosition = ship.getX();
        double yFirstPosition = ship.getY();
        ship.move();
        double xSecondPosition = ship.getX();
        double ySecondPosition = ship.getY();
        if (Math.abs(_firstAxis) == 1) {
            boolean conditionOfTheXAxisIsPassed = ((yFirstPosition <= _yCoordinate) && (_yCoordinate <= ySecondPosition)) || ((yFirstPosition >= _yCoordinate) && (_yCoordinate >= ySecondPosition));
            if (_firstAxis == 1){
                return ((xFirstPosition >= _xCoordinate) && (xSecondPosition >= _xCoordinate)) && conditionOfTheXAxisIsPassed;
            } else if (_firstAxis == -1) {
                return ((xFirstPosition <= _xCoordinate) && (xSecondPosition <= _xCoordinate)) && conditionOfTheXAxisIsPassed;
            } else {
                boolean conditionOfTheYAxisIsPassed = ((xFirstPosition <= _xCoordinate) && (_xCoordinate <= xSecondPosition)) || ((xFirstPosition >= _xCoordinate) && (_xCoordinate >= xSecondPosition));
                if (_secondAxis == 2) {
                    return ((yFirstPosition <= _yCoordinate) && (ySecondPosition <= _yCoordinate)) && conditionOfTheYAxisIsPassed;
                } else if (_secondAxis == -2) {
                    return ((yFirstPosition >= _yCoordinate) && (ySecondPosition >= _yCoordinate)) && conditionOfTheYAxisIsPassed;
                }
            }
        }
        return false;
    }

    public void isFirstAxisPassed(ShipModel ship){
        _firstAxisPassed = isAxisPassed(ship);
    }

    public void isSecondAxisPassed(ShipModel ship){
        _bothAxisPassed = _firstAxisPassed && isAxisPassed(ship);
    }
}
