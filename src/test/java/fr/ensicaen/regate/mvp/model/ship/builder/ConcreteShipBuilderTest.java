package fr.ensicaen.regate.mvp.model.ship.builder;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStationProxy;
import fr.ensicaen.regate.mvp.model.map.wind.WeatherStationServer;
import fr.ensicaen.regate.mvp.model.map.wind.WindDirection;
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

class ConcreteShipBuilderTest {
    private ConcreteShipBuilder _builder;
    private WeatherStationProxy _weatherStationProxy;
    @Mock
    private WeatherStationServer _weatherStationServerMocked;

    @BeforeEach
    void setUp() {
        _builder = new ConcreteShipBuilder();
        _weatherStationServerMocked = mock(WeatherStationServer.class);
        doReturn(4.).when(_weatherStationServerMocked).getSpeedWindInKnot();
        doReturn(WindDirection.SOUTH).when(_weatherStationServerMocked).getWindDirection();
        _weatherStationProxy = new WeatherStationProxy(_weatherStationServerMocked);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void xPositionShouldBeThree() {
        _builder.setPosition(3, 0);
        assertEquals(3, _builder.getStartX());
    }

    @Test
    void yPositionShouldBeThree() {
        _builder.setPosition(0, 3);
        assertEquals(3, _builder.getStartY());
    }

    @Test
    void sailShouldBeNormalSail() {
        _builder.setSail(new NormalSail());
        assertInstanceOf(NormalSail.class, _builder.getSail());
    }

    @Test
    void sailShouldBeLargeSail() {
        _builder.setSail(new LargeSailDecorator(new NormalSail()));
        assertInstanceOf(LargeSailDecorator.class, _builder.getSail());
    }

    @Test
    void crewShouldBeNormalCrew() {
        _builder.setCrew(new NormalCrew());
        assertInstanceOf(NormalCrew.class, _builder.getCrew());
    }

    @Test
    void crewShouldBeMaxCrew() {
        _builder.setCrew(new MaxCrewDecorator(new NormalCrew()));
        assertInstanceOf(MaxCrewDecorator.class, _builder.getCrew());
    }

    @Test
    void polarShouldBeFigaroPolar() throws FileNotFoundException {
        _builder.setPolar("polaire-figaro.pol");
        assertEquals("polaire-figaro.pol", _builder.getPolar().getPolarName());
    }

    @Test
    void polarShouldBeFigaroOceanis37() throws FileNotFoundException {
        _builder.setPolar("polaire-oceanis-37.pol");
        assertEquals("polaire-oceanis-37.pol", _builder.getPolar().getPolarName());
    }

    @Test
    void windShouldBeAWeatherStationProxy() {
        _builder.setWind(_weatherStationProxy);
        assertInstanceOf(WeatherStationProxy.class, _builder.getWind());
    }

    @Test
    void windSpeedShouldBe4() {
        _builder.setWind(_weatherStationProxy);
        assertEquals(4., _builder.getWind().getSpeedWindInKnot());
    }

    @Test
    void windDirectionShouldBeSouth() {
        _builder.setWind(_weatherStationProxy);
        assertEquals(WindDirection.SOUTH, _builder.getWind().getWindDirection());
    }
}