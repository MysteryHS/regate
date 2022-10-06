package fr.ensicaen.genielogiciel.mvp.model.map.wind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindProxyTest {
    private Wind _windProxy;
    @BeforeEach
    public void setUp() {
        _windProxy = new WindProxy(49.183, -0.371);
    }

    @AfterEach
    public void tearDown() {
        _windProxy = null;
    }
    @Test
    void windSpeedShouldEqualThree() {
        assertEquals(4., _windProxy.getWindKnot());
    }

    @Test
    void windDirectionShouldEqualSouth() {
        assertEquals(WindDirection.SOUTH, _windProxy.getWindDirection());
    }
}