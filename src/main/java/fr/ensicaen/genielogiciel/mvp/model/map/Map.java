package fr.ensicaen.genielogiciel.mvp.model.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private int _width;
    private int _height;
    private int _nbBuoy;
    private int _nbSand;
    private int _nbWater;
    private double _longitude;
    private double _latitude;
    private final List<Buoy> _buoys = new ArrayList<>();
    private final List<Tile> _tiles = new ArrayList<>();

    private static final int NumberDatasWithBuoys = 3;
    private static final int NumberDatasWithNotBuoys = 2;
    private static final String pathToMapFolder = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/";

    public Map(String filename) throws IOException {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        readFile( pathToMapFolder + filename);
    }

    public Map(){
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        _nbSand = 0;
        _nbWater = 0;
    }

    public Map(int width, int height, int nbBuoy){
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

    public int getNbSand() {
        return _nbSand;
    }

    public int getNbWater() {
        return _nbWater;
    }

    public List<Buoy> getBuoys() {
        return _buoys;
    }

    public List<Tile> getTiles() {
        return _tiles;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    public char getType(int X, int Y){
        for (Tile t : _tiles) {
            if ((t.getCoordinateX() == X) && (t.getCoordinateY() == Y)) {
                return t.getSymbol();
            }
        }
        return 0;
    }

    public Buoy getBuoy(int X, int Y) {
        for (Buoy b : _buoys){
            if ((b.getXCoordinate() == X) && (b.getYCoordinate() == Y)) {
                return b;
            }
        }
        return null;
    }

    public void readFile(String filename) throws IOException {
        File inputFile = new File(filename);
        Scanner myReader = new Scanner(inputFile);
        heightWidthAndNumberBuoyRecovery(readWidthHeightAndNumberBuoysInFirstLine(myReader));
        longitudeAndLatitudeRecovery(readLongitudeAndLatitudeSecondLine(myReader));
        readMap(myReader);
    }

    public String readWidthHeightAndNumberBuoysInFirstLine(Scanner myReader) {
        return myReader.nextLine();
    }

    public void  heightWidthAndNumberBuoyRecovery(String information) {
        String[] delimitation = information.split(" ");
        if (delimitation.length == NumberDatasWithBuoys) {
            _width = Integer.parseInt(delimitation[0]);
            _height = Integer.parseInt(delimitation[1]);
            _nbBuoy = Integer.parseInt(delimitation[2]);
        } else {
            if (delimitation.length == NumberDatasWithNotBuoys) {
                _width = Integer.parseInt(delimitation[0]);
                _height = Integer.parseInt(delimitation[1]);
                _nbBuoy = 0;
            }
        }
    }

    public String readLongitudeAndLatitudeSecondLine(Scanner myReader) {
        return myReader.nextLine();
    }

    public void longitudeAndLatitudeRecovery(String information){
        String[] delimitation = information.split(" ");
        _longitude = Double.parseDouble(delimitation[0]);
        _latitude = Double.parseDouble(delimitation[1]);
    }

    public void collectBuoys(Scanner myReader) {
        String[] delimitation;
        int XCoordinate;
        int YCoordinate;
        for (int i = 0 ; i < _nbBuoy ; i++) {
            String dataBuoy = myReader.nextLine();
            delimitation = dataBuoy.split(" ");
            XCoordinate = Integer.parseInt(delimitation[0]);
            YCoordinate = Integer.parseInt(delimitation[1]);
            Buoy b = new Buoy(XCoordinate, YCoordinate);
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
                    _nbWater++;
                } else if (c == '.') {
                    _tiles.add(new Sand(i, y));
                    _nbSand++;
                }
            }
            y++;
        }
    }
}
