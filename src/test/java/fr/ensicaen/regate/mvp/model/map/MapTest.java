package fr.ensicaen.regate.mvp.model.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testHeightWidthAndNumberBuoyRecovery() {
        GameMap m = new GameMap();
        m.heightWidthAndNumberBuoyRecovery("50 50 0");
        assertEquals(50, m.getHeight());
        assertEquals(50, m.getWidth());
        assertEquals(0, m.getNbBuoy());
    }
}
