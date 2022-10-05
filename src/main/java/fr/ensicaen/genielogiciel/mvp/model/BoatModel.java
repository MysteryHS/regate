package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.sail.Sail;
import java.util.HashMap;

public class BoatModel {

    private static final HashMap<Integer, HashMap<Integer, Integer>> polar = new HashMap<>();

    static {
        for(int i=0;i<181;i+=10){
            polar.put(i, new HashMap<>());
            for(int j=4;j<31;j+=2){
                polar.get(i).put(j, i<=30?0:j*2);
            }
        }
    }
    private double _x = 580;
    private double _y = 480;
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

    private double getSpeed(){
        int angleToWind = (((int)(Math.abs(_wind.getWindDirection().getAngle()-_anglePositive))/10)*10);
        return polar.get(angleToWind<180?angleToWind:Math.abs(360-angleToWind)).get(_wind.getWindKnot());
    }

    public void move() {
        double speed = getSpeed();
        _dx = Math.sin(_anglePositive * Math.PI / 180) * _sail.getShipSpeed(_anglePositive-180) * speed;
        _dy = -Math.cos(_anglePositive * Math.PI / 180) * _sail.getShipSpeed(_anglePositive-180) * speed;
        _x += _dx;
        _y += _dy;
    }
}
