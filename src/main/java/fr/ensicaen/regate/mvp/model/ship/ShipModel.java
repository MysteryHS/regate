package fr.ensicaen.regate.mvp.model.ship;

import fr.ensicaen.regate.mvp.model.ship.command.Move;
import fr.ensicaen.regate.mvp.model.ship.crew.Crew;
import fr.ensicaen.regate.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.regate.mvp.model.ship.sail.Sail;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShipModel {
    private double _x;
    private double _y;
    private double _height;
    private double _width;
    private final double _initialX;
    private final double _initialY;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;
    private final double _speedRatio = 0.03;
    private final Sail _sail;
    private final Crew _crew;
    private final WeatherStation _weatherStation;
    private final DataPolar _polar;
    private boolean _isReplaying = false;
    private boolean _isCollisioning = false;
    private boolean _isFixed = false;
    private final Timer _timer;
    private final List<Move> _commands = new ArrayList<>();

    public ShipModel(Sail sail, Crew crew, WeatherStation weatherStation, DataPolar polarName, Timer timer, double x, double y){
        _sail = sail;
        _crew = crew;
        _timer = timer;
        _weatherStation = weatherStation;
        _polar = polarName;
        _x = x;
        _y = y;
        _initialX = x;
        _initialY = y;
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

    public void setCollision(boolean coll){
        _isCollisioning = coll;
    }
    public void setHeight(double height){
        _height = height;
    }
    public void setWidth(double width){
        _width = width;
    }
    public double getNewPositionRight(){
        return _x + _dx + _width;
    }
    public double getNewPositionLeft(){
        return _x + _dx;
    }
    public double getNewPositionTop(){
        return _y + _dy;
    }
    public double getNewPositionBottom(){
        return _y + _dy + _height;
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
        if(_isReplaying){
            _isFixed = true;
            _timer.cancel();
            return;
        }
        _isReplaying = true;
        if(_commands.size() != 0){
            for(Move move : _commands){
                _timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        move.execute();
                    }
                }, move.getDelay());
            }
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    _isReplaying = false;
                    _isFixed = true;
                    _dx = 0;
                    _dy = 0;
                }
            }, delayEnd);
            _commands.clear();
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
        double _inertia = 0.004;
        if(newSpeed<currentSpeed - _inertia){
            return currentSpeed - _inertia;
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

    public Sail getSail() {
        return _sail;
    }

    public Crew getCrew() {
        return _crew;
    }

    public String getPolarName() {
        return _polar.getPolarName();
    }

    public String getImageSRC() {
        if(_polar.getPolarName().equals("polaire-figaro.pol")){
            return "file:src/main/resources/fr/ensicaen/regate/mvp/images/boats/small_boat.png";
        }
        return "file:src/main/resources/fr/ensicaen/regate/mvp/images/boats/big_boat.png";
    }
}
