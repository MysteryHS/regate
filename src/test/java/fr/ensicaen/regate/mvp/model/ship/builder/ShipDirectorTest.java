package fr.ensicaen.regate.mvp.model.ship.builder;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStationProxy;
import fr.ensicaen.regate.mvp.model.map.wind.WindDirection;
import fr.ensicaen.regate.mvp.model.ship.ShipModel;
import fr.ensicaen.regate.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.regate.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.regate.mvp.model.ship.sail.LargeSailDecorator;
import fr.ensicaen.regate.mvp.model.ship.sail.NormalSail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ShipDirectorTest {

    private ShipDirector _director;
    private ShipModel _ship;
    @Mock
    private WeatherStationProxy _mockedWind;

    @BeforeEach
    void setUp() {
        _mockedWind = mock(WeatherStationProxy.class);
        doReturn(WindDirection.SOUTH).when(_mockedWind).getWindDirection();
        doReturn(4.).when(_mockedWind).getSpeedWindInKnot();
        _director = new ShipDirector(new ConcreteShipBuilder());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shipShouldBeAnInstanceOfNormalSailAndNormalCrewOfFigaro() throws FileNotFoundException {
        _ship = _director.buildFigaro().buildNormalSail().buildNormalCrew().addWind(_mockedWind).build();
        assertInstanceOf(NormalSail.class, _ship.getSail());
        assertInstanceOf(NormalCrew.class, _ship.getCrew());
        assertEquals("polaire-figaro.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfLargerSailAndNormalCrewOfFigaro() throws FileNotFoundException {
        _ship = _director.buildFigaro().buildLargerSail().buildNormalCrew().addWind(_mockedWind).build();
        assertInstanceOf(LargeSailDecorator.class, _ship.getSail());
        assertInstanceOf(NormalCrew.class, _ship.getCrew());
        assertEquals("polaire-figaro.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfNormalSailAndMaxCrewOfFigaro() throws FileNotFoundException {
        _ship = _director.buildFigaro().buildNormalSail().buildMaxCrew().addWind(_mockedWind).build();
        assertInstanceOf(NormalSail.class, _ship.getSail());
        assertInstanceOf(MaxCrewDecorator.class, _ship.getCrew());
        assertEquals("polaire-figaro.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfLargerSailAndMaxCrewOfFigaro() throws FileNotFoundException {
        _ship = _director.buildFigaro().buildLargerSail().buildMaxCrew().addWind(_mockedWind).build();
        assertInstanceOf(LargeSailDecorator.class, _ship.getSail());
        assertInstanceOf(MaxCrewDecorator.class, _ship.getCrew());
        assertEquals("polaire-figaro.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfNormalSailAndNormalCrewOfOceanis37() throws FileNotFoundException {
        _ship = _director.buildOceanis37().buildNormalSail().buildNormalCrew().addWind(_mockedWind).build();
        assertInstanceOf(NormalSail.class, _ship.getSail());
        assertInstanceOf(NormalCrew.class, _ship.getCrew());
        assertEquals("polaire-oceanis-37.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfLargerSailAndNormalCrewOfOceanis37() throws FileNotFoundException {
        _ship = _director.buildOceanis37().buildLargerSail().buildNormalCrew().addWind(_mockedWind).build();
        assertInstanceOf(LargeSailDecorator.class, _ship.getSail());
        assertInstanceOf(NormalCrew.class, _ship.getCrew());
        assertEquals("polaire-oceanis-37.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfNormalSailAndMaxCrewOfOceanis37() throws FileNotFoundException {
        _ship = _director.buildOceanis37().buildNormalSail().buildMaxCrew().addWind(_mockedWind).build();
        assertInstanceOf(NormalSail.class, _ship.getSail());
        assertInstanceOf(MaxCrewDecorator.class, _ship.getCrew());
        assertEquals("polaire-oceanis-37.pol", _ship.getPolarName());
    }

    @Test
    void shipShouldBeAnInstanceOfLargerSailAndMaxCrewOfOceanis37() throws FileNotFoundException {
        _ship = _director.buildOceanis37().buildLargerSail().buildMaxCrew().addWind(_mockedWind).build();
        assertInstanceOf(LargeSailDecorator.class, _ship.getSail());
        assertInstanceOf(MaxCrewDecorator.class, _ship.getCrew());
        assertEquals("polaire-oceanis-37.pol", _ship.getPolarName());
    }
}