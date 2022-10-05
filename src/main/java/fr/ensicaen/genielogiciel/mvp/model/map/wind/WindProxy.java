package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindProxy implements Wind {
    private final WindServer _windServer;

    public WindProxy(double longitude, double latitude) {
        _windServer = new WindServer(longitude, latitude);
    }
    @Override
    public int getWindKnot() {
        return _windServer.getWindKnot();
    }
    @Override
    public WindDirection getWindDirection() {
        return _windServer.getWindDirection();
    }
}
