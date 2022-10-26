package fr.ensicaen.genielogiciel.mvp.model.map;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStationProxy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    private int _width;
    private int _height;
    private int _nbBuoy;
    private int _nbSand;
    private int _nbWater;

    private WeatherStation _weatherStation;
    private final List<Buoy> _buoys = new ArrayList<Buoy>();
    private final List<Tile> _tiles = new ArrayList<Tile>();

    public GameMap(String filename) throws IOException {
        readFile(filename);
        //TODO wind not configured
        //_weatherStation = new WeatherStationProxy(0., 0.);
    }

    public Tile getTile(int x,int y) {
        return _tiles.get(y*_width+x);
    }

    public List<Buoy> getBuoys() {
        return _buoys;
    }

    public int getHeight() {
        return _height;
    }

    public int getWidth() {
        return _width;
    }

    public GameMap() throws IOException {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        _nbSand = 0;
        _nbWater = 0;
        //readFirstLineInFile("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
    }

    public GameMap(int width, int height, int nbBuoy){
        _width = width;
        _height = height;
        _nbBuoy = nbBuoy;
    }

    public int getNbBuoy() {
        return _nbBuoy;
    }

    public List<Tile> getTiles() {
        return _tiles;
    }

    public WeatherStation getWind() { return _weatherStation; }

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
        heightWidthAndNumberBuoyRecovery(readFirstLineInFile(myReader));
        readMap(myReader);
    }

    public String readFirstLineInFile(Scanner myReader) throws IOException {
        return myReader.nextLine();
    }

    public void  heightWidthAndNumberBuoyRecovery(String information) {
        String[] delimitation = information.split(" ");
        _width = Integer.parseInt(delimitation[0]);
        _height = Integer.parseInt(delimitation[1]);
        _nbBuoy = Integer.parseInt(delimitation[2]);
    }

    public void collectBuoys(Scanner myReader) throws IOException {
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

    public void readMap(Scanner reader) throws IOException {
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
