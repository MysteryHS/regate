package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShipModel {
    private double _x = 5;
    private double _y = 5;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;
    private final double _speedRatio = 0.01;
    private final Sail _sail;
    private final Crew _crew;
    private final WeatherStation _weatherStation;
    private final DataPolar _polar;

    public ShipModel(Sail sail, Crew crew, WeatherStation weatherStation, DataPolar polarName, double start_x, double start_y){
        _sail = sail;
        _crew = crew;
        _weatherStation = weatherStation;
        _polar = polarName;
        _x = start_x;
        _y = start_y;
    }

    public ShipModel(Sail sail, Crew crew, WeatherStation weatherStation, String polarName){
        _sail = sail;
        _crew = crew;
        _weatherStation = weatherStation;
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

    private double getSpeed() {
        int angleToWind360 = (((int)(Math.abs(_weatherStation.getWindDirection().getAngle()-_anglePositive))/10)*10);
        double angle = angleToWind360<180?angleToWind360:Math.abs(360-angleToWind360);
        return _polar.getPolarValues(angle, _weatherStation.getSpeedWindInKnot()) * _sail.getShipSpeed(_anglePositive-180) * _crew.getShipSpeed(_anglePositive-180)* _speedRatio;
    }

    private double getNewSpeedX() {
        return getSpeed() * Math.sin(_anglePositive * Math.PI / 180);
    }

    private double getNewSpeedY() {
        return getSpeed() * (-Math.cos(_anglePositive * Math.PI / 180));
    }

    private double getInertiaSpeed(double newSpeed, double currentSpeed){
        double _inertia = 0.05;
        if(newSpeed<currentSpeed- _inertia){
            return currentSpeed- _inertia;
        }
        return Math.min(newSpeed, currentSpeed + _inertia);
    }

    public void move() {
        _dx = getInertiaSpeed(getNewSpeedX(), _dx);
        _dy = getInertiaSpeed(getNewSpeedY(), _dy);
        _x += _dx;
        _y += _dy;
    }

    public Sail getSail() {
        return _sail;
    }

    public Crew getCrew() {
        return _crew;
    }

    public WeatherStation getWind() {
        return _weatherStation;
    }

    public String getPolarName() {
        return _polar.getPolarName();
    }

    public String getImageSRC() {
        if(_polar.getPolarName().equals("polaire-figaro.pol")){
            return "file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boats/small_boat.png";
        } else {
            return "file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boats/big_boat.png";
        }
    }
}
