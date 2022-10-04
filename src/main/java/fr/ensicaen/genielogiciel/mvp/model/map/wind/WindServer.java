package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public class WindServer implements Wind {
    private final double _longitude;
    private final double _latitude;

    public WindServer(double longitude, double latitude) {
        _longitude = longitude;
        _latitude = latitude;
    }

    @Override
    public WindData queryWindData() {
        return new WindData(WindDirection.SOUTH, 3);
    }
}
