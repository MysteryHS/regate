package fr.ensicaen.genielogiciel.mvp.model.sail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LargeSailDecoratorTest {

    @Test
    void getSpeedRotation() {
    }

    @Test
    void getShipSpeed() {
        LargeSailDecorator normalSail = new LargeSailDecorator(new NormalSail());
        assertEquals(1, normalSail.getShipSpeed(60));
        assertEquals(4, normalSail.getShipSpeed(0));
        assertEquals(2.5, normalSail.getShipSpeed(30));
        assertEquals(1, normalSail.getShipSpeed(-80));
    }
}