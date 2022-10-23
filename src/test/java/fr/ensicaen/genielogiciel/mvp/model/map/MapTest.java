package fr.ensicaen.genielogiciel.mvp.model.map;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testReadFirstLine() throws IOException {
        Map m = new Map();
        assertEquals("50 50 1", m.readFirstLineInFile(new Scanner(new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt"))));
    }

    @Test
    public void testHeightWidthAndNumberBuoyRecovery() throws IOException {
        Map m = new Map();
        m.heightWidthAndNumberBuoyRecovery("50 50 0");
        assertEquals(50, m.getHeight());
        assertEquals(50, m.getWidth());
        assertEquals(0, m.getNbBuoy());
    }

    @Test
    public void testCollectBuoys() throws IOException {
        Map m = new Map(50,50,1);
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        assertEquals(5, m.getBuoys().get(0).getXCoordinate());
        assertEquals(5, m.getBuoys().get(0).getYCoordinate());
    }

    @Test
    public void testReadMap() throws IOException {
        Map m = new Map(50,50,1);
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        m.readMap(myReader);
        int i = 0;
        int j = 0;
        for (Tile elem : m.getTiles()) {
            Tile tile = new Water(i, j);
            assertEquals(tile.getCoordinateX(), elem.getCoordinateX());
            assertEquals(tile.getCoordinateY(), elem.getCoordinateY());
            assertEquals(tile.getSymbol(), elem.getSymbol());
            if (i == m.getWidth()-1) {
                i = 0;
                j++;
            } else {
                i++;
            }
        }
    }

    @Test
    public void testGetType() throws IOException {
        Map m = new Map(50,50,1);
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        m.readMap(myReader);
        char c = m.getType(0,0);
        assertEquals('~', c);
        c = m.getType(-1, 0);
        assertEquals(0, c);
    }

    @Test
    public void testReadFile() throws IOException {
        Map m = new Map("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
        m.displayInformationMap();
    }
}
