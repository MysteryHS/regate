package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.sail.Sail;

public class BoatModel {
    private double _x = 100;
    private double _y = 100;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;

    private final Sail _sail;
    private final Wind _wind;

    public BoatModel(Sail sail, Wind wind){
        _sail = sail;
        _wind = wind;
    }
    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void rotate( double angle ) {
        _anglePositive = (360 + _anglePositive + (_sail.getSpeedRotation()*angle)) % 360;
    }

    public double getAngle() {
        return _anglePositive;
    }

    public double getDx() {
        return _dx ;
    }

    public double getDy() {
        return _dy ;
    }

    public void move() {
        _dx = Math.sin(_anglePositive * Math.PI / 180) * _sail.getShipSpeed(_anglePositive-180);
        _dy = -Math.cos(_anglePositive * Math.PI / 180) * _sail.getShipSpeed(_anglePositive-180);
        _x += _dx;
        _y += _dy;
    }
}
