package fr.ensicaen.regate.mvp.model.map.wind;

import java.io.IOException;
import java.util.Date;

public class WeatherStationProxy implements WeatherStation {
    private WindData _windData;
    private final double _longitude;
    private final double _latitude;
    private final WeatherStationServer _server;
    private Date _date;
    private final long _time = 86400000;

    public WeatherStationProxy(double longitude, double latitude) {
        _longitude = longitude;
        _latitude = latitude;
        _date = new Date(0);
        _windData = requestWindData(longitude, latitude);
        _server = null;
    }

    public WeatherStationProxy(WeatherStationServer server) {
        _server = server;
        _longitude = 0.;
        _latitude = 0.;
        _date = new Date(0);
        _windData = requestWindData(0, 0);
    }

    @Override
    public double getSpeedWindInKnot() {
        if ( !isDateOutdated( _time ) ) {
            return _windData.getSpeedWindInKnot();
        }
        _windData = requestWindData(_longitude, _latitude);
        return getSpeedWindInKnot();
    }

    @Override
    public WindDirection getWindDirection() {
        if ( !isDateOutdated( _time ) ) {
            return _windData.getDirection();
        }
        _windData = requestWindData(_longitude, _latitude);
        return getWindDirection();
    }

    protected boolean isDateOutdated( long timeInMillisecond ) {
        return new Date(_date.getTime() + timeInMillisecond).compareTo(_date) <= 0;
    }

    protected WindData requestWindData( double longitude, double latitude ) {
        _date = new Date();
        if ( _server != null ) {
            return new WindData( _server.getWindDirection(), _server.getSpeedWindInKnot() );
        }
        WeatherStationServer server = null;
        try {
            server = new WeatherStationServer( longitude, latitude );
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assert server != null;
        return new WindData( server.getWindDirection(), server.getSpeedWindInKnot() );
    }

}
