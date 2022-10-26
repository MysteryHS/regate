package fr.ensicaen.genielogiciel.mvp.model.ship;

import fr.ensicaen.genielogiciel.mvp.model.ship.command.Move;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShipModel {
    private double _x;
    private double _y;

    private double _initialX;
    private double _initialY;
    private double _dx = 0;
    private double _dy = 0;
    private double _anglePositive = 0;
    private final double _inertia = 0.05;
    private final double _speedRatio = 0.8;
    private final Sail _sail;
    private final Crew _crew;
    private final Wind _wind;
    private final DataPolar _polar;
    private boolean _isReplaying = false;
    private boolean _canMove = true;

    private final Timer _timer = new Timer();

    private final List<Move> _commands = new ArrayList<>();
    public ShipModel(Sail sail, Crew crew, Wind wind, DataPolar polarName, double x, double y){
        _sail = sail;
        _crew = crew;
        _wind = wind;
        _polar = polarName;
        _x = x;
        _y = y;
        _initialX = x;
        _initialY = y;
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

    public boolean isReplaying(){
        return _isReplaying;
    }

    public double getSpeedRatio() {
        return _speedRatio;
    }

    public void performCommand(Move move){
        _commands.add(move);
        move.execute();
    }

    public void replay(long delayEnd){
        _x = _initialX;
        _y = _initialY;
        _dx = 0;
        _dy = 0;
        _anglePositive = 0;
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
                    purgeTimer();
                    _canMove = false;
                    _isReplaying = false;
                    _dx = 0;
                    _dy = 0;
                }
            }, delayEnd);
            _commands.clear();
        }
    }

    public void purgeTimer(){
        _timer.cancel();
        _timer.purge();
    }

    public void rotate( double angle ) {
        if(_canMove){
            _anglePositive = (360 + _anglePositive + (_sail.getSpeedRotation()* _crew.getSpeedRotation()*angle)) % 360;
        }
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
        if(_canMove){
            _dx = getInertiaSpeed(getNewSpeedX(), _dx);
            _dy = getInertiaSpeed(getNewSpeedY(), _dy);
            _x += _dx;
            _y += _dy;
        }
    }

    public Wind getWind() {
        return _wind;
    }
}
