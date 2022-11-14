package fr.ensicaen.regate.mvp.model.map.wind;

public class WindData {
    private final WindDirection _direction;
    private final double _knot;

    public WindData(WindDirection direction, double knot) {
        _direction = direction;
        _knot = knot;
    }

    public WindDirection getDirection() {
        return _direction;
    }

    public double getSpeedWindInKnot() {
        return _knot;
    }
}
