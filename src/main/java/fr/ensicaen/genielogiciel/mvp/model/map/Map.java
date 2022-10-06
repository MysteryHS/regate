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
        readFirstLineInFile(filename);
    }

    public Tile getTile(int x,int y) {
        return _tiles.get(y*_width+x);
    }

    public int getHeight() {
        return _height;
    }

    public int getWidth() {
        return _width;
    }

    public Map() throws IOException {
        readFirstLineInFile("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
    }

    private void readFirstLineInFile(String filename) throws IOException {
        int XCoordinate;
        int YCoordinate;
        File inputFile = new File(filename);
        Scanner myReader = new Scanner(inputFile);
        String firstLine = myReader.nextLine();
        String[] delims = firstLine.split(" ");
        _width = Integer.parseInt(delims[0]);
        _height = Integer.parseInt(delims[1]);
        _nbBuoy = Integer.parseInt(delims[2]);
        for (int i = 0 ; i < _nbBuoy ; i++) {
            String dataBuoy = myReader.nextLine();
            delims = dataBuoy.split(" ");
            XCoordinate = Integer.parseInt(delims[0]);
            YCoordinate = Integer.parseInt(delims[1]);
            Buoy b = new Buoy(XCoordinate, YCoordinate);
            _buoys.add(b);
        }
        readMap(myReader);
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
