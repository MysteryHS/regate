package fr.ensicaen.regate.mvp.model.map.wind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class WeatherStationProxyTest {
    private WeatherStationProxy _weatherStationProxy;
    private WindData _windData;
    @Mock
    private WeatherStationServer _weatherStationServerMocked;
    @BeforeEach
    public void setUp() {
        _windData = new WindData(WindDirection.SOUTH, 4.);
        _weatherStationServerMocked = mock(WeatherStationServer.class);
        doReturn(4.).when(_weatherStationServerMocked).getSpeedWindInKnot();
        doReturn(WindDirection.SOUTH).when(_weatherStationServerMocked).getWindDirection();
        _weatherStationProxy = new WeatherStationProxy(_weatherStationServerMocked);
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

    @Test
    void windSpeedShouldBeEqualsToWindData() {
        assertEquals(_windData.getSpeedWindInKnot(), _weatherStationProxy.requestWindData(0., 0.).getSpeedWindInKnot());
    }

    @Test
    void windDirectionShouldBeEqualsToWindData() {
        assertEquals(_windData.getDirection(), _weatherStationProxy.requestWindData(0., 0.).getDirection());
    }
}