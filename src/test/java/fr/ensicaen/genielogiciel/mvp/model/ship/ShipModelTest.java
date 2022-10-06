package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindDirection;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ShipModelTest {
    private ShipModel _ship;
    private double _angle;
    private double _knot;
    private double _speedRatio;
    private double _maxSpeed;
    @Mock
    private Wind _mockedWind;
    @Mock
    private DataPolar _mockedPolar;

    @BeforeEach
    void setUp() {
        _mockedWind = mock(WindProxy.class);
        _knot = 6.;
        _mockedPolar = mock(DataPolar.class);
        doReturn(WindDirection.SOUTH).when(_mockedWind).getWindDirection();
        doReturn(_knot).when(_mockedWind).getWindKnot();
        _angle = 60.;
    }
    @AfterEach
    void tearDown() {
        _ship = null;
    }

    @Test
    void shipSpeedShouldBeEqualsToMaxSpeed() {
        _ship = new ShipModel(new NormalSail(), new NormalCrew(), _mockedWind, _mockedPolar );
        _speedRatio = _ship.getSpeedRatio();
        _maxSpeed = new MocktDataPolar().getPolarValues(_angle, _knot) * _speedRatio;
        doReturn(new MocktDataPolar().getPolarValues(_angle, _knot)).when(_mockedPolar).getPolarValues(_angle, _knot);
        _ship.rotate(_angle);
        for ( int i = 0; i < 100; i++ ) {
            _ship.move();
        }
        assertEquals(_maxSpeed, Math.sqrt(_ship.getDx()* _ship.getDx() + _ship.getDy()* _ship.getDy()));
    }
}