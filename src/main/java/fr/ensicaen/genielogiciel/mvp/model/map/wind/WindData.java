package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindData {
    private final WindDirection _direction;
    private final double _knot;

    public WindData(WindDirection direction, double knot) {
        this._direction = direction;
        this._knot = knot;
    }

    public WindDirection getDirection() {
        return _direction;
    }

    public double getKnot() {
        return _knot;
    }
}
