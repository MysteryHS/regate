package fr.ensicaen.regate.mvp.model.map.wind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindDataTest {

    private WindData _windData;
    private WindDirection _uselessDirection;
    private double _uselessSpeed;

    @BeforeEach
    void setUp() {
        _uselessDirection = WindDirection.SOUTH;
        _uselessSpeed = 0.;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void windSpeedInKnotShouldBeEqualsTo4() {
        _windData = new WindData(_uselessDirection, 4.);
        assertEquals(4., _windData.getSpeedWindInKnot());
    }

    @Test
    void windSpeedInKnotShouldBeEqualsTo12() {
        _windData = new WindData(_uselessDirection, 12.);
        assertEquals(12., _windData.getSpeedWindInKnot());
    }

    @Test
    void windDirectionShouldBeNorthEast() {
        _windData = new WindData(WindDirection.NORTH_EAST, _uselessSpeed);
        assertEquals(WindDirection.NORTH_EAST, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeNorthWest() {
        _windData = new WindData(WindDirection.NORTH_WEST, _uselessSpeed);
        assertEquals(WindDirection.NORTH_WEST, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeEast() {
        _windData = new WindData(WindDirection.EAST, _uselessSpeed);
        assertEquals(WindDirection.EAST, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeWest() {
        _windData = new WindData(WindDirection.WEST, _uselessSpeed);
        assertEquals(WindDirection.WEST, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeSouth() {
        _windData = new WindData(WindDirection.SOUTH, _uselessSpeed);
        assertEquals(WindDirection.SOUTH, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeSouthEast() {
        _windData = new WindData(WindDirection.SOUTH_EAST, _uselessSpeed);
        assertEquals(WindDirection.SOUTH_EAST, _windData.getDirection());
    }

    @Test
    void windDirectionShouldBeSouthWest() {
        _windData = new WindData(WindDirection.SOUTH_WEST, _uselessSpeed);
        assertEquals(WindDirection.SOUTH_WEST, _windData.getDirection());
    }
}