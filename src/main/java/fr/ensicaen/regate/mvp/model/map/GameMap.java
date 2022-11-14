package fr.ensicaen.regate.mvp.model.map;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.regate.mvp.model.map.wind.WeatherStationProxy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    private int _width;
    private int _height;
    private int _nbBuoy;
    private int _xStartPoint;
    private int _yStartPoint;
    private final List<Buoy> _buoys = new ArrayList<>();
    private final List<Tile> _tiles = new ArrayList<>();
    private WeatherStation _weatherStation;

    private static final int numberDataWithBuoys = 3;
    private static final int numberDataWithNotBuoys = 2;
    private static final String pathToMapFolder = "./src/main/resources/fr/ensicaen/regate/mvp/maps/";

    public GameMap(String filename) throws IOException {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        readFile(pathToMapFolder + filename);
    }

    public GameMap() {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
    }

    public GameMap(int width, int height, int nbBuoy){
        _width = width;
        _height = height;
        _nbBuoy = nbBuoy;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getNbBuoy() {
        return _nbBuoy;
    }

    public double getStartX() {
        return _xStartPoint;
    }

    public double getStartY() {
        return _yStartPoint;
    }

    public List<Buoy> getBuoys() {
        return _buoys;
    }

    public List<Tile> getTiles() {
        return _tiles;
    }

    public WeatherStation getWind() {
        return _weatherStation;
    }

    public char getType(int x, int y){
        for (Buoy b : _buoys) {
            if ((b.getX() == x) && (b.getY() == y)) {
                return '~';
            }
        }
        for (Tile t : _tiles) {
            if ((t.getX() == x) && (t.getY() == y)) {
                return t.getSymbol();
            }
        }
        return 0;
    }

    public void readFile(String filename) throws IOException {
        File inputFile = new File(filename);
        Scanner myReader = new Scanner(inputFile);
        heightWidthAndNumberBuoyRecovery(readWidthHeightAndNumberBuoysInFirstLine(myReader));
        longitudeAndLatitudeRecovery(readLongitudeAndLatitudeSecondLine(myReader));
        startPointRecovery(readStartPointThirdLine(myReader));
        collectBuoys(myReader);
        readMap(myReader);
        myReader.close();
    }

    public String readWidthHeightAndNumberBuoysInFirstLine(Scanner myReader) {
        return myReader.nextLine();
    }

    public void  heightWidthAndNumberBuoyRecovery(String information) {
        String[] delimitation = information.split(" ");
        _width = Integer.parseInt(delimitation[0]);
        _height = Integer.parseInt(delimitation[1]);
        _nbBuoy = delimitation.length == numberDataWithNotBuoys ? 0 : Integer.parseInt(delimitation[2]);
    }

    public String readLongitudeAndLatitudeSecondLine(Scanner myReader) {
        return myReader.nextLine();
    }

    public void longitudeAndLatitudeRecovery(String information){
        String[] delimitation = information.split(" ");
        double longitude = Double.parseDouble(delimitation[0]);
        double latitude = Double.parseDouble(delimitation[1]);
        _weatherStation = new WeatherStationProxy(longitude, latitude);
    }

    public String readStartPointThirdLine(Scanner myReader){
        return myReader.nextLine();
    }

    public void startPointRecovery(String information){
        String[] delimitation = information.split(" ");
        _xStartPoint = Integer.parseInt(delimitation[0]);
        _yStartPoint = Integer.parseInt(delimitation[1]);
    }

    public void collectBuoys(Scanner myReader) {
        String[] delimitation;
        int xCoordinate;
        int yCoordinate;
        for (int i = 0 ; i < _nbBuoy ; i++) {
            String dataBuoy = myReader.nextLine();
            delimitation = dataBuoy.split(" ");
            xCoordinate = Integer.parseInt(delimitation[0]);
            yCoordinate = Integer.parseInt(delimitation[1]);
            Buoy b = new Buoy(xCoordinate, yCoordinate);
            _buoys.add(b);
        }
    }

    public void readMap(Scanner reader) {
        int y = 0;
        while (reader.hasNextLine()) {
            String s = reader.nextLine();
            for (int i = 0 ; i < s.length() ; i++) {
                char c = s.charAt(i);
                if (c == '~') {
                    _tiles.add(new Water(i, y));
                } else if (c == '.') {
                    _tiles.add(new Sand(i, y));
                }
            }
            y++;
        }
    }

    public boolean isPassingBuoyNumber(int buoyNumber, int boatX, int boatY){
        if(buoyNumber > _buoys.size()-1) {
            return false;
        }
        return _buoys.get(buoyNumber).isPassed(boatX, boatY);
    }
}
