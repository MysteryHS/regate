package fr.ensicaen.regate.mvp.model.ship;

public class MocktDataPolar {
    public MocktDataPolar() {
    }

    public double getPolarValues(double angle, double knot) {
        if ( angle < 30. ) {
            return 0.;
        }
        return (0.0019886363636 * angle * knot) + 2.261336363636;
    }
}
