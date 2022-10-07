package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;

public class ShipModel {
    private double _x = 580;
    private double _y = 480;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;
    private final double _inertia = 0.05;
    private final double _speedRatio = 0.8;
    private final Sail _sail;
    private final Crew _crew;
    private final Wind _wind;
    private final DataPolar _polar;

    @Deprecated
    public ShipModel(Sail sail, Crew crew, Wind wind, DataPolar polarName){
        _sail = sail;
        _crew = crew;
        _wind = wind;
        _polar = polarName;
    }

    public ShipModel(Sail sail, Crew crew, Wind wind, String polarName){
        _sail = sail;
        _crew = crew;
        _wind = wind;
        try {
            _polar = new DataPolar(polarName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public double getInertia() {
        return _inertia;
    }

    public double getSpeedRatio() {
        return _speedRatio;
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
        int angleToWind360 = (((int)(Math.abs(_wind.getWindDirection().getAngle()-_anglePositive))/10)*10);
        double angle = angleToWind360<180?angleToWind360:Math.abs(360-angleToWind360);
        return _polar.getPolarValues(angle, _wind.getWindKnot()) * _sail.getShipSpeed(_anglePositive-180) * _crew.getShipSpeed(_anglePositive-180)* _speedRatio;
    }

    private double getNewSpeedX(){
        return getSpeed() * Math.sin(_anglePositive * Math.PI / 180);
    }

    private double getNewSpeedY(){
        return getSpeed() * (-Math.cos(_anglePositive * Math.PI / 180));
    }

    private double getInertiaSpeed(double newSpeed, double currentSpeed){
        if(newSpeed<currentSpeed-_inertia){
            return currentSpeed-_inertia;
        }
        return Math.min(newSpeed, currentSpeed + _inertia);
    }

    public void move() {
        _dx = getInertiaSpeed(getNewSpeedX(), _dx);
        _dy = getInertiaSpeed(getNewSpeedY(), _dy);
        _x += _dx;
        _y += _dy;
    }
}
