package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindData {
    private WindDirection _direction;
    private int _knot;

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
