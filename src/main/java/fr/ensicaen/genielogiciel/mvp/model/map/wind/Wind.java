package fr.ensicaen.genielogiciel.mvp.model.map.wind;

// FIXME je pense que le nom devrait plutot être WeatherStation
public interface Wind {
    WindDirection getWindDirection();
    double getWindKnot();
}
