package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.sail.Sail;

public class BoatModel {
    private double _x = 580;
    private double _y = 480;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;

    private final Sail _sail;
    private final Crew _crew;
    private final Wind _wind;
    private final DataPolar _polar;

    public BoatModel(Sail sail, Crew crew, Wind wind, DataPolar polar){
        _sail = sail;
        _crew = crew;
        _wind = wind;
        _polar = polar;
    }
    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void rotate( double angle ) {
        _anglePositive = (360 + _anglePositive + (_sail.getSpeedRotation()* _crew.getSpeedRotation()*angle)) % 360;
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

    private double getSpeed(){
        double ratio = 0.3;
        int angleToWind360 = (((int)(Math.abs(_wind.getWindDirection().getAngle()-_anglePositive))/10)*10);
        double angle = angleToWind360<180?angleToWind360:Math.abs(360-angleToWind360);
        return _polar.getPolarValues(angle, _wind.getWindKnot())*ratio;
    }

    public void move() {
        double speed = getSpeed() * _sail.getShipSpeed(_anglePositive-180) * _crew.getShipSpeed(_anglePositive-180);
        _dx = Math.sin(_anglePositive * Math.PI / 180) * speed;
        _dy = -Math.cos(_anglePositive * Math.PI / 180) * speed;
        _x += _dx;
        _y += _dy;
    }
}
