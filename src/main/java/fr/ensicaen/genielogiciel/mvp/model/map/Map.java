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
    private List<Buoy> _buoys = new ArrayList<Buoy>();
    private List<Tile> _tiles = new ArrayList<Tile>();

    public Map(String filename) throws IOException {
        readFile(filename);
    }

    public Map() throws IOException {
        _width = 0;
        _height = 0;
        _nbBuoy = 0;
        //readFirstLineInFile("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
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

    public List<Buoy> getBuoys() {
        return _buoys;
    }

    public void readFile(String filename) throws IOException {
        File inputFile = new File(filename);
        Scanner myReader = new Scanner(inputFile);
        heightWidthAndNumberBuoyRecovery(readFirstLineInFile(myReader));
    }
    public String readFirstLineInFile(Scanner myReader) throws IOException {
        String firstLine = myReader.nextLine();
        myReader.close();
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

    private void readMap(Scanner reader) throws IOException {
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
}
