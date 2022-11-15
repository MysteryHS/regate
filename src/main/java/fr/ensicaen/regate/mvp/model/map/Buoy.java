package fr.ensicaen.regate.mvp.model.map;

public class Buoy {
    private final int _xCoordinate;
    private final int _yCoordinate;
    private static final double hitDistance = 1.5;

    public Buoy(int xCoordinate, int yCoordinate) {
        _xCoordinate = xCoordinate;
        _yCoordinate = yCoordinate;
    }

    public int getX() {
        return _xCoordinate;
    }

    public int getY() {
        return _yCoordinate;
    }

    public boolean isPassed(int x,int y) {
        if(Math.abs(_xCoordinate-x)<hitDistance) {
            return Math.abs(_yCoordinate - y) < hitDistance;
        }
        return false;
    }
}
