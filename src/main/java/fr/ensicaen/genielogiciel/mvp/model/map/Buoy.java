package fr.ensicaen.genielogiciel.mvp.model.map;

public class Buoy {
    private final char _symbol = '#';
    private int _xCoordinate;
    private int _yCoordinate;

    public Buoy(int xCoordinate, int yCoordinate) {
        _xCoordinate = xCoordinate;
        _xCoordinate = yCoordinate;
    }

    public void displayBuoy(){
        System.out.println(_xCoordinate);
        System.out.println(_yCoordinate);
    }

    public int getX() {return _xCoordinate;}

    public int getY() {return _yCoordinate;}

}
