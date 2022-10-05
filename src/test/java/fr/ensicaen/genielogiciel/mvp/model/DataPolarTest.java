package fr.ensicaen.genielogiciel.mvp.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DataPolarTest {
    @Test
    void test() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        System.out.println(polar.getPolarValues(60, 20));
    }

    @Test
    void polarValueShouldBeEqualsTo12_50() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(12.50, polar.getPolarValues(160, 28));
    }

    @Test
    void polarValueShouldBeEqualsTo9925() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(9.925, polar.getPolarValues(120, 17));
    }

    @Test
    void polarValueShouldBeEqualsTo10665() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(10.665, polar.getPolarValues(95, 26));
    }

    @Test
    void polarValueShouldBeEqualsTo450() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(4.50, polar.getPolarValues(35, 7));
    }
}