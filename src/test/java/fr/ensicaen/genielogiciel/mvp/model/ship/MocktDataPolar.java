package fr.ensicaen.genielogiciel.mvp.model.ship;

public class MocktDataPolar {
    public MocktDataPolar() {
    }

    public double getPolarValues(double angle, double knot) {
        if ( angle < 30. ) {
            System.out.println(angle);
            return 0.;
        }
        return (0.0019886363636 * angle * knot) + 2.261336363636;
    }
}
