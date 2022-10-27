package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private int _xCoordinate;
    private int _yCoordinate;
    private static final double hitDistance = 1;

    public Buoy(int xCoordinate, int yCoordinate) {
        _xCoordinate = xCoordinate;
        _yCoordinate = yCoordinate;
    }

    public int getX() {return _xCoordinate;}

    public int getY() {return _yCoordinate;}


    public boolean isPassed(int x,int y) {
        if(Math.abs(_xCoordinate-x)<hitDistance) {
            if(Math.abs(_yCoordinate-y)<hitDistance) {
                return true;
            }
        }
        return false;
    }
}
