package fr.ensicaen.regate.mvp.model.ship;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DataPolarTest {
    @Test
    void polarFigaroValueShouldBeEqualsTo12_50() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(12.50, polar.getPolarValues(160, 28));
    }
    @Test
    void polarOceanisValueShouldBeEqualsTo10_89() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-oceanis-37.pol");
        assertEquals(10.89, polar.getPolarValues(160, 28));
    }

    @Test
    void polarFigaroValueShouldBeEqualsTo9925() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(9.925, polar.getPolarValues(120, 17));
    }

    @Test
    void polarOceanisValueShouldBeEqualsTo8615() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-oceanis-37.pol");
        assertEquals(8.615, polar.getPolarValues(120, 17));
    }

    @Test
    void polarFigaroValueShouldBeEqualsTo10665() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(10.665, polar.getPolarValues(95, 26));
    }

    @Test
    void polarOceanisValueShouldBeEqualsTo9235() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-oceanis-37.pol");
        assertEquals(9.235, polar.getPolarValues(95, 26));
    }

    @Test
    void polarFigaroValueShouldBeEqualsTo450() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-figaro.pol");
        assertEquals(4.50, polar.getPolarValues(35, 7));
    }

    @Test
    void polarOceanisValueShouldBeEqualsTo4125() throws FileNotFoundException {
        DataPolar polar = new DataPolar("polaire-oceanis-37.pol");
        assertEquals(4.125, polar.getPolarValues(35, 7));
    }
}