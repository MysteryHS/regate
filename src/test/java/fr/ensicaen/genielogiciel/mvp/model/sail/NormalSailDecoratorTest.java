package fr.ensicaen.genielogiciel.mvp.model.sail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalSailDecoratorTest {

    @Test
    void getSpeedRotation() {
    }

    @Test
    void getShipSpeed() {
        NormalSailDecorator normalSail = new NormalSailDecorator(new NormalSail());
        assertEquals(2, normalSail.getShipSpeed(60));
        assertEquals(4, normalSail.getShipSpeed(0));
        assertEquals(3, normalSail.getShipSpeed(30));
        assertEquals(2, normalSail.getShipSpeed(-80));
    }
}