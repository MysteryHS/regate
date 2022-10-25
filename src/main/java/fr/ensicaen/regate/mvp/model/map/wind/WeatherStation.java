package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public interface WeatherStation {
    WindDirection getWindDirection();
    double getWindSpeedInKnots();
}
