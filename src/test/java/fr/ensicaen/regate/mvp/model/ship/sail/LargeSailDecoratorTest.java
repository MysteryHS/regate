package fr.ensicaen.regate.mvp.model.ship.sail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LargeSailDecoratorTest {

    @Test
    void getSpeedRotation() {

        LargeSailDecorator largeSail = new LargeSailDecorator(new NormalSail());
        assertEquals(0.8, largeSail.getSpeedRotation());
    }

    @Test
    void getShipSpeed() {
        LargeSailDecorator largeSail = new LargeSailDecorator(new NormalSail());
        assertEquals(1, largeSail.getShipSpeed(60));
        assertEquals(1.2, largeSail.getShipSpeed(0));
        assertEquals(1.1, largeSail.getShipSpeed(30));
        assertEquals(1, largeSail.getShipSpeed(-80));
    }
}