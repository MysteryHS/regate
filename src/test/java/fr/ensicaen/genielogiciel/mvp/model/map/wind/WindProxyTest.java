package fr.ensicaen.genielogiciel.mvp.model.map.wind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindProxyTest {
    Wind windProxy = new WindProxy(49.183, -0.371);
    @Test
    void windSpeedShouldEqualThree() {
        assertEquals(3, windProxy.getWindKnot());
    }

    @Test
    void windDirectionShouldEqualSouth() {
        assertEquals(WindDirection.SOUTH, windProxy.getWindDirection());
    }
}