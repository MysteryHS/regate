package fr.ensicaen.regate.mvp.model.ship.sail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalSailTest {

    @Test
    void getSpeedRotation() {
        NormalSail normalSail = new NormalSail();
        assertEquals(1, normalSail.getSpeedRotation());
    }

    @Test
    void getShipSpeed() {
        NormalSail normalSail = new NormalSail();
        assertEquals(1, normalSail.getShipSpeed(0));
        assertEquals(1, normalSail.getShipSpeed(60));
        assertEquals(1, normalSail.getShipSpeed(70));
    }
}