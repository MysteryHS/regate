package fr.ensicaen.regate.mvp.model.ship.crew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxCrewDecoratorTest {

    @Test
    void getSpeedRotation_shouldBeEqualsTo_1_4() {
        MaxCrewDecorator max_crew = new MaxCrewDecorator(new NormalCrew());
        assertEquals(1.4, max_crew.getSpeedRotation());
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_0_8_WithAngle_0() {
        MaxCrewDecorator max_crew = new MaxCrewDecorator(new NormalCrew());
        assertEquals(0.8, max_crew.getShipSpeed(0));
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_0_8_WithAngle_60() {
        MaxCrewDecorator max_crew = new MaxCrewDecorator(new NormalCrew());
        assertEquals(0.8, max_crew.getShipSpeed(60));
    }

    @Test
    void getShipSpeed_shouldBeEqualsTo_0_8_WithAngle_70() {
        MaxCrewDecorator max_crew = new MaxCrewDecorator(new NormalCrew());
        assertEquals(0.8, max_crew.getShipSpeed(70));
    }
}