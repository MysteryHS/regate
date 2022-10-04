package fr.ensicaen.genielogiciel.mvp.model.map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private final int _width;
    private final int _height;
    private final int _nbBuoy;
    private List<Buoy> _buoys = new ArrayList<Buoy>();

    public Map(int width, int height, int nbBuoy) {
        _width = width;
        _height = height;
        _nbBuoy = nbBuoy;
    }

    public String readFirstLineInFile(String filename) throws IOException{
        try {
            File inputFile = new File(filename);
            Scanner myReader = new Scanner(inputFile);
            String line = myReader.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;
    }
}
