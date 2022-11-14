package fr.ensicaen.regate.mvp.model.ship.crew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalCrewTest {

    @Test
    void getSpeedRotation_shouldBeEqualsTo_1() {
        NormalCrew normal_crew = new NormalCrew();
        assertEquals(1, normal_crew.getSpeedRotation());
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_1_WithAngle_0() {
        NormalCrew normal_crew = new NormalCrew();
        assertEquals(1, normal_crew.getShipSpeed(0));
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_1_WithAngle_60() {
        NormalCrew normal_crew = new NormalCrew();
        assertEquals(1, normal_crew.getShipSpeed(60));
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_1_WithAngle_70() {
        NormalCrew normal_crew = new NormalCrew();
        assertEquals(1, normal_crew.getShipSpeed(70));
    }
}