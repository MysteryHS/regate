package fr.ensicaen.regate.mvp.model.ship;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.regate.mvp.model.map.wind.WindDirection;
import fr.ensicaen.regate.mvp.model.map.wind.WeatherStationProxy;
import fr.ensicaen.regate.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.regate.mvp.model.ship.sail.NormalSail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ShipModelTest {
    private double _angle;
    private double _knot;
    @Mock
    private WeatherStation _mockedWeatherStation;
    @Mock
    private DataPolar _mockedPolar;
    @Mock
    private Timer _mockedTimer;

    @BeforeEach
    void setUp() {
        _mockedWeatherStation = mock(WeatherStationProxy.class);
        _mockedTimer = mock(Timer.class);
        _knot = 6.;
        _mockedPolar = mock(DataPolar.class);
        doReturn(WindDirection.SOUTH).when(_mockedWeatherStation).getWindDirection();
        doReturn(_knot).when(_mockedWeatherStation).getSpeedWindInKnot();
        _angle = 60.;
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void shipSpeedShouldBeEqualsToMaxSpeed() {
        ShipModel _ship = new ShipModel(new NormalSail(), new NormalCrew(), _mockedWeatherStation, _mockedPolar, _mockedTimer,17, 13);
        double _speedRatio = _ship.getSpeedRatio();
        double _maxSpeed = new MocktDataPolar().getPolarValues(_angle, _knot) * _speedRatio;
        doReturn(new MocktDataPolar().getPolarValues(_angle, _knot)).when(_mockedPolar).getPolarValues(_angle, _knot);
        _ship.rotate(_angle);
        for ( int i = 0; i < 100; i++ ) {
            _ship.move();
        }
        assertEquals(_maxSpeed, Math.sqrt(_ship.getDx()* _ship.getDx() + _ship.getDy()* _ship.getDy()));
    }
}