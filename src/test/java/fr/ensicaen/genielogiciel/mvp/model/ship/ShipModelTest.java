package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindData;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindDirection;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShipModelTest {
    private ShipModel _ship;
    private double _angle;
    private double _knot;
    private double _acc;
    @Mock
    private Wind _mockedWind;
    @Mock
    private DataPolar _mockedPolar;

    @BeforeEach
    void setUp() {
        _mockedWind = mock(WindProxy.class);
        _mockedPolar = mock(DataPolar.class);
        doReturn(WindDirection.SOUTH).when(_mockedWind).getWindDirection();
        doReturn(4.).when(_mockedWind).getWindKnot();
        _knot = _mockedWind.getWindKnot();
        _angle = 60.;
        _acc = 0.02;
    }
    @AfterEach
    void tearDown() {
        _ship = null;
    }

    @Test
    void todo() {
        _ship = new ShipModel(new NormalSail(), new NormalCrew(), _mockedWind, _mockedPolar );
        System.out.println(_ship.getX());
        System.out.println(_ship.getDx());
        System.out.println(_ship.getAngle());
        doReturn(new TestDataPolar().getPolarValues(_angle, _knot)).when(_mockedPolar).getPolarValues(_angle, _knot);
        double dx = 0.;
        _ship.rotate(_angle);
        for ( int i = 0; i < 100; i++ ) {
            _ship.move();
            dx += _acc;
        }
        System.out.println( (double) Math.round(_ship.getX() * 100.) / 100. );
        System.out.println(_ship.getDx());
        System.out.println(dx);
        System.out.println(_ship.getAngle());
        assertEquals(_angle, _ship.getAngle());
    }

}