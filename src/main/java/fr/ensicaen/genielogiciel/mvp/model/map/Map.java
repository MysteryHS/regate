package fr.ensicaen.genielogiciel.mvp.model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private int _width;
    private int _height;
    private int _nbBuoy;
    private List<Buoy> _buoys = new ArrayList<Buoy>();

    public static void main(String[] args) throws IOException {
        Map m = new Map("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
        m.displayInformationMap();
    }

    public Map(String filename) throws IOException {
        readFirstLineInFile(filename);
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
    }

    private void displayInformationMap(){
        System.out.println("Width = " + _width);
        System.out.println("Height = " + _height);
        System.out.println("nbBuoy = " + _nbBuoy);
    }
}
