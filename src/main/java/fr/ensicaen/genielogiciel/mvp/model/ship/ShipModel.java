package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.ship.command.Move;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShipModel {
    private double _x;
    private double _y;
    private double _height;
    private double _width;
    private double _initialX;
    private double _initialY;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;
    private final double _inertia = 0.004;
    private final double _speedRatio = 0.04;
    private final Sail _sail;
    private final Crew _crew;
    private final WeatherStation _wind;
    private final DataPolar _polar;
    private boolean _isReplaying = false;

    private boolean _isCollisioning = false;

    private boolean _isFixed = false;
    private final List<Move> _commands = new ArrayList<>();
    public ShipModel(Sail sail, Crew crew, WeatherStation wind, DataPolar polarName, double x, double y){
        _sail = sail;
        _crew = crew;
        _wind = wind;
        _polar = polarName;
        _x = x;
        _y = y;
        _initialX = x;
        _initialY = y;
    }

    public ShipModel(Sail sail, Crew crew, WeatherStation wind, String polarName){
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

    public void setCollision(boolean coll){
        _isCollisioning = coll;
    }
    public void setHeight(double height){
        _height = height;
    }
    public double getHeight(){
        return _height;
    }
    public void setWidth(double width){
        _width = width;
    }
    public double getWidth(){
        return _width;
    }

    public void performCommand(Move move){
        _commands.add(move);
        move.execute();
    }

    public void resetSpeed(){
        _dx = 0;
        _dy = 0;
    }

    public void replay(long delayEnd){

        resetSpeed();
        _x = _initialX;
        _y = _initialY;
        _anglePositive = 0;
        _isReplaying = true;
        if(_commands.size() != 0){
            Timer timer = new Timer();
            for(Move move : _commands){
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        move.execute();
                    }
                }, move.getDelay());
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    timer.purge();
                    _isReplaying = false;
                    _isFixed = true;
                    _dx = 0;
                    _dy = 0;
                }
            }, delayEnd);
            _commands.clear();
            //TODO is waiting for last to purge, even if the app is closed
        }
    }

    public void rotate( double angle ) {
        _anglePositive = (360 + _anglePositive + (_sail.getSpeedRotation()* _crew.getSpeedRotation()*angle)) % 360;
    }

    public boolean canRotate(){
        return !(_isFixed || _isReplaying);
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
        return _polar.getPolarValues(angle, _wind.getWindSpeedInKnots()) * _sail.getShipSpeed(_anglePositive-180) * _crew.getShipSpeed(_anglePositive-180)* _speedRatio;
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
        if(!_isFixed && !_isCollisioning){
            _x += _dx;
            _y += _dy;
        }
    }

    public WeatherStation getWind() {
        return _wind;
    }
}
