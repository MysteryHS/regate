package fr.ensicaen.genielogiciel.mvp.model.map;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// FIXME attention a l'ambiguite avec Map du Java
public class Map {
    private int _width;
    private int _height;
    private int _nbBuoy;
    private int _nbSand;
    private int _nbWater;

    private Wind _wind;
    private List<Buoy> _buoys = new ArrayList<Buoy>(); // FIXME mettre en final + utiliser la declaration diamant (ArrayList<>())
    private List<Tile> _tiles = new ArrayList<Tile>();

    public Map(String filename) throws IOException {
        readFile(filename);
        _wind = new WindProxy(0., 0.);
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

    public Map() throws IOException {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        _nbSand = 0;
        _nbWater = 0;
        //readFirstLineInFile("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
    }

    public Map(int width, int height, int nbBuoy){
        _width = width;
        _height = height;
        _nbBuoy = nbBuoy;
    }


    public int getNbBuoy() {
        return _nbBuoy;
    }

    public int getNbSand() {
        return _nbSand;
    } // FIXME jamais utilisé

    public int getNbWater() {
        return _nbWater;
    } // FIXME jamais utilisé


    public List<Tile> getTiles() {
        return _tiles;
    }

    public Wind getWind() { return _wind; }

    public char getType(int X, int Y){ // FIXME respecter la casse Java
        for (Buoy elem : _buoys) { // FIXME elem -> b ou buoy
            if ((elem.getXCoordinate() == X) && (elem.getYCoordinate() == Y)) {
                return '~';
            }
        }
        for (Tile t : _tiles) {
            if ((t.getCoordinateX() == X) && (t.getCoordinateY() == Y)) {
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
        String firstLine = myReader.nextLine(); // FIXME utiliser la version sport return myReader.nextLine();
        return firstLine;
    }

    public void  heightWidthAndNumberBuoyRecovery(String information) {
        String[] delimitation = information.split(" ");
        _width = Integer.parseInt(delimitation[0]);
        _height = Integer.parseInt(delimitation[1]);
        _nbBuoy = Integer.parseInt(delimitation[2]);
    }

    public void collectBuoys(Scanner myReader) throws IOException {
        String[] delimitation;
        int XCoordinate; // FIXME utiliser la casse Java !
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

    public void displayInformationMap(){ // FIXME jamais utilisé
        System.out.println("Width = " + _width);
        System.out.println("Height = " + _height);
        System.out.println("nbBuoy = " + _nbBuoy);
        for (Buoy elem : _buoys) {
            elem.displayBuoy();
        }
        int index = 0;
        for (Tile t : _tiles) {
            t.displayTile();
            if (index == _width -1) {
                index = 0;
                System.out.println();
            } else {
                index++;
            }
        }
    }
}
