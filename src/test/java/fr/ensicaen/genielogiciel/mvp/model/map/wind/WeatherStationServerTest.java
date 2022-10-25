package fr.ensicaen.genielogiciel.mvp.model.map.wind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeatherStationServerTest {
    private WeatherStationServer _weatherStationServer;
    @BeforeEach
    void setUp() throws IOException {
        _weatherStationServer = new WeatherStationServer(-0.25, 49.23);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void knotShouldBeEqualsTo10() {
        assertEquals(10, _weatherStationServer.kilometersToKnot(18.52));
    }

    @Test
    void windDirectionShouldBeEqualsToNorth() {
        assertEquals(WindDirection.NORTH, _weatherStationServer.stringToWindDirection("N"));
    }
    @Test
    void windDirectionShouldBeEqualsToNorthEast() {
        assertEquals(WindDirection.NORTH_EAST, _weatherStationServer.stringToWindDirection("NE"));
    }
    @Test
    void windDirectionShouldBeEqualsToEast() {
        assertEquals(WindDirection.EAST, _weatherStationServer.stringToWindDirection("E"));
    }
    @Test
    void windDirectionShouldBeEqualsToSouthEast() {
        assertEquals(WindDirection.SOUTH_EAST, _weatherStationServer.stringToWindDirection("SE"));
    }
    @Test
    void windDirectionShouldBeEqualsToSouth() {
        assertEquals(WindDirection.SOUTH, _weatherStationServer.stringToWindDirection("S"));
    }
    @Test
    void windDirectionShouldBeEqualsToSouthWest() {
        assertEquals(WindDirection.SOUTH_WEST, _weatherStationServer.stringToWindDirection("SO"));
    }
    @Test
    void windDirectionShouldBeEqualsToWest() {
        assertEquals(WindDirection.WEST, _weatherStationServer.stringToWindDirection("O"));
    }
    @Test
    void windDirectionShouldBeEqualsToNorthWest() {
        assertEquals(WindDirection.NORTH_WEST, _weatherStationServer.stringToWindDirection("NO"));
    }
}