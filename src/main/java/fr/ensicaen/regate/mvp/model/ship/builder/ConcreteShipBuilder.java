package fr.ensicaen.regate.mvp.model.ship.builder;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.regate.mvp.model.ship.DataPolar;
import fr.ensicaen.regate.mvp.model.ship.ShipModel;
import fr.ensicaen.regate.mvp.model.ship.crew.Crew;
import fr.ensicaen.regate.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;
import java.util.Timer;

public class ConcreteShipBuilder implements ShipBuilder {
    private Sail _sail;
    private Crew _crew;
    private DataPolar _polar;
    private WeatherStation _wind;
    private Timer _timer;
    private double _startX = 5;
    private double _startY = 5;

    @Override
    public void setPosition(double x, double y) {
        _startX = x;
        _startY = y;
    }

    @Override
    public void setSail(Sail sail) {
        _sail = sail;
    }

    @Override
    public void setCrew(Crew crew) {
        _crew = crew;
    }

    @Override
    public void setPolar(String polarNameFromResourcePackage) throws FileNotFoundException {
        _polar = new DataPolar( polarNameFromResourcePackage );
    }
    @Override
    public void setWind(WeatherStation wind) {
        _wind = wind;
    }
    @Override
    public void setTimer(Timer timer){
        _timer = timer;
    }
    @Override
    public ShipModel getResult() {
        return new ShipModel( _sail, _crew, _wind, _polar, _timer,_startX,_startY );
    }

    protected Sail getSail() {
        return _sail;
    }

    protected Crew getCrew() {
        return _crew;
    }

    protected DataPolar getPolar() {
        return _polar;
    }

    protected WeatherStation getWind() {
        return _wind;
    }

    protected double getStartX() {
        return _startX;
    }

    protected double getStartY() {
        return _startY;
    }
}
