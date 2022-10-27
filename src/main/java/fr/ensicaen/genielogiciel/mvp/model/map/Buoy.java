package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private final char _symbol = '#';
    private int _xCoordinate;
    private int _yCoordinate;
    private final int _firstAxis;
    private final int _secondAxis; //-1 to the top

    public Buoy(int xCoordinate, int yCoordinate, int xAxis, int yAxis) {
        _xCoordinate = xCoordinate;
        _yCoordinate = yCoordinate;
        _firstAxis = xAxis;
        _secondAxis = yAxis;
    }

    public void displayBuoy(){
        System.out.println(_xCoordinate);
        System.out.println(_yCoordinate);
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
