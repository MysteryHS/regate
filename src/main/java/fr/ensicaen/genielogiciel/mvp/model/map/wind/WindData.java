package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindData {
    private final WindDirection _direction;
    private final int _knot;

    public WindData(WindDirection direction, int knot) {
        this._direction = direction;
        this._knot = knot;
    }

    public WindDirection getDirection() {
        return _direction;
    }

    public int getKnot() {
        return _knot;
    }
}
