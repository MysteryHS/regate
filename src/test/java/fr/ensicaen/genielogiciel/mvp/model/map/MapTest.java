package fr.ensicaen.genielogiciel.mvp.model.map;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    final String filePath = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt";
    @Test
    public void testReadFirstLine() throws IOException {
        GameMap m = new GameMap();
        assertEquals("50 50 2", m.readFirstLineInFile(new Scanner(new File(filePath))));
    }

    @Test
    public void testHeightWidthAndNumberBuoyRecovery() throws IOException {
        GameMap m = new GameMap();
        m.heightWidthAndNumberBuoyRecovery("50 50 0");
        assertEquals(50, m.getHeight());
        assertEquals(50, m.getWidth());
        assertEquals(0, m.getNbBuoy());
    }

    //TODO expected 5 but was 0 ??
    /*
    @Test
    public void testCollectBuoys() throws IOException {
        GameMap m = new GameMap(50,50,1);
        File inputFile = new File(filePath);
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        assertEquals(5, m.getBuoys().get(0).getX());
        assertEquals(5, m.getBuoys().get(0).getY());
    }
     */

    //TODO expected 0 but was 1 ??
    /*
    @Test
    public void testReadMap() throws IOException {
        GameMap m = new GameMap(50,50,1);
        File inputFile = new File(filePath);
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        m.readMap(myReader);
        int i = 0;
        int j = 0;
        for (Tile elem : m.getTiles()) {
            Tile tile = new Water(i, j);
            assertEquals(tile.getX(), elem.getY());
            if (i == m.getWidth()-1) {
                i = 0;
                j++;
            } else {
                i++;
            }
        }
    }
     */

    @Test
    public void testGetType() throws IOException {
        GameMap m = new GameMap(50,50,1);
        File inputFile = new File(filePath);
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        m.readMap(myReader);
        char c = m.getType(-1, 0);
        assertEquals(0, c);
    }
}
