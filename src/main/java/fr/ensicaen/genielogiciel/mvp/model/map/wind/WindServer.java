package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindServer implements Wind {
    private final double _longitude;
    private final double _latitude;
    private final WindData _windData;

    public WindServer(double longitude, double latitude) {
        _longitude = longitude;
        _latitude = latitude;
        _windData = queryWindDataFromJSON();
    }

    private WindData queryWindDataFromJSON() {
        return new WindData(WindDirection.SOUTH, 4);
    }

    @Override
    public int getWindKnot() {
        return _windData.getKnot();
    }

    @Override
    public WindDirection getWindDirection() {
        return _windData.getDirection();
    }
}
