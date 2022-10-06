package fr.ensicaen.genielogiciel.mvp.model.ship;

public class TestDataPolar {
    public TestDataPolar() {
    }

    public double getPolarValues(double angle, double knot) {
        if ( angle < 30. ) {
            System.out.println("BUG");
            return 0.;
        }
        System.out.println("angle = " + angle + " knot = " + knot);
        return (0.0019886363636 * angle * knot) + 2.261336363636;
    }
}
