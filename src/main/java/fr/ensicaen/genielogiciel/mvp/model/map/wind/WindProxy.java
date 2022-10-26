package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindProxy implements WeatherStation {
    private final WindServer _windServer;

    public WindProxy(double longitude, double latitude) {
        _windServer = new WindServer(longitude, latitude);
    }
    @Override
    public double getWindSpeedInKnots() {
        return _windServer.getWindSpeedInKnots();
    }
    @Override
    public WindDirection getWindDirection() {
        return _windServer.getWindDirection();
    }
}
