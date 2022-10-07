package fr.ensicaen.genielogiciel.mvp.model.map;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testReadWidthHeightAndNumberBuoysInFirstLine() throws IOException {
        Map m1 = new Map();
        assertEquals("50 50 1", m1.readWidthHeightAndNumberBuoysInFirstLine(new Scanner(new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt"))));

        Map m2 = new Map();
        assertEquals("100 50", m2.readWidthHeightAndNumberBuoysInFirstLine(new Scanner(new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt"))));

    }

    @Test
    public void testHeightWidthAndNumberBuoyRecovery() {
        Map m = new Map();
        m.heightWidthAndNumberBuoyRecovery("50 50");
        assertEquals(50, m.getHeight());
        assertEquals(50, m.getWidth());
        assertEquals(0, m.getNbBuoy());

        m.heightWidthAndNumberBuoyRecovery("100 1000 50");
        assertEquals(1000, m.getHeight());
        assertEquals(100, m.getWidth());
        assertEquals(50, m.getNbBuoy());

        Map m1 = new Map();
        m1.heightWidthAndNumberBuoyRecovery("50");
        assertEquals(0, m1.getHeight());
        assertEquals(0, m1.getWidth());
        assertEquals(0, m1.getNbBuoy());
    }

    @Test
    public void testReadLongitudeAndLatitudeSecondLine() throws IOException {
        Map m1 = new Map();
        Scanner myReader = new Scanner(new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt"));
        myReader.nextLine();
        assertEquals("49.283 -0.25", m1.readLongitudeAndLatitudeSecondLine(myReader));
    }

    @Test
    public void testLongitudeAndLatitudeRecovery() {
        Map m = new Map();
        m.longitudeAndLatitudeRecovery("49.283 -0.25");
        assertEquals(49.283, m.getLongitude());
        assertEquals(-0.25, m.getLatitude());
    }


    @Test
    public void testCollectBuoys() throws IOException {
        Map m = new Map(50,50,1);
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        assertEquals(5, m.getBuoys().get(0).getXCoordinate());
        assertEquals(5, m.getBuoys().get(0).getYCoordinate());
        
        Map m2 = new Map(100,50,0);
        File inputFile2 = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte2.txt");
        Scanner myReader2 = new Scanner(inputFile2);
        myReader2.nextLine();
        m2.collectBuoys(myReader2);
        assertEquals(0, m2.getBuoys().size());
    }

    @Test
    public void testReadMap() throws IOException {
        Map m = new Map(50,50,1);
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
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
        File inputFile = new File("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
        Scanner myReader = new Scanner(inputFile);
        myReader.nextLine();
        m.collectBuoys(myReader);
        m.readMap(myReader);
        char c = m.getType(0,0);
        assertEquals('~', c);
        c = m.getType(-1, 0);
        assertEquals(0, c);
    }
}
