package fr.ensicaen.genielogiciel.mvp.model.map.wind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class WeatherStationProxyTest {
    private WeatherStationProxy _weatherStationProxy;
    @Mock
    private WeatherStationServer _weatherStationServer;
    @BeforeEach
    public void setUp() {
        _weatherStationServer = mock(WeatherStationServer.class);
        doReturn(4.).when(_weatherStationServer).getSpeedWindInKnot();
        doReturn(WindDirection.SOUTH).when(_weatherStationServer).getWindDirection();
        _weatherStationProxy = new WeatherStationProxy( _weatherStationServer);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void windIsDateOutdatedShouldBeFalse() {
        assertFalse(_weatherStationProxy.isDateOutdated(100000));
    }

    @Test
    void windIsDateOutdatedShouldBeTrue() {
        assertTrue(_weatherStationProxy.isDateOutdated(0));
    }

    @Test
    void windSpeedShouldEqualThree() {
        assertEquals(4., _weatherStationProxy.getSpeedWindInKnot());
    }

    @Test
    void windDirectionShouldEqualSouth() {
        assertEquals(WindDirection.SOUTH, _weatherStationProxy.getWindDirection());
    }
}