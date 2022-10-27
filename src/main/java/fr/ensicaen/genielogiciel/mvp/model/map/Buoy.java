package fr.ensicaen.genielogiciel.mvp.model.map;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class Buoy {
    private final char _symbol = '#';
    private final int _xCoordinate;
    private final int _yCoordinate;
    private final int _firstAxis;
    private final int _secondAxis; //-1 to the top

    public Buoy(int XCoordinate, int YCoordinate, int xAxis, int yAxis) {
        _xCoordinate = XCoordinate;
        _yCoordinate = YCoordinate;
        _firstAxis = xAxis;
        _secondAxis = yAxis;
    }

    public int getX() {return _xCoordinate;}

    public int getY() {return _yCoordinate;}

    public boolean isAxisPassed(int boatX, int boatY){
        if (_firstAxis == -1) {
            if (boatX > _xCoordinate) {
                return false;
            }
            if (_secondAxis == -1) {
                if (boatY > _yCoordinate) {
                    return false;
                }
            } else if (_secondAxis == 1) {
                if (boatY < _yCoordinate) {
                    return false;
                }
            }
        } else if (_firstAxis == 1) {
            if (boatX < _xCoordinate ) {
                return false;
            }
            if (_secondAxis == -1) {
                if (boatY > _yCoordinate) {
                    return false;
                }
            } else if (_secondAxis == 1) {
                if (boatY < _yCoordinate) {
                    return false;
                }
            }
        }
        return true;
    }
}
