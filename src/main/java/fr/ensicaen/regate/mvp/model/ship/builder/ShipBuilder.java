package fr.ensicaen.regate.mvp.model.ship.builder;

import fr.ensicaen.regate.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.regate.mvp.model.ship.ShipModel;
import fr.ensicaen.regate.mvp.model.ship.crew.Crew;
import fr.ensicaen.regate.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;
import java.util.Timer;

public interface ShipBuilder {

    void setPosition(double x, double y);
    void setSail(Sail sail );
    void setCrew(Crew crew );
    void setPolar(String polarNameFromResourcePackage ) throws FileNotFoundException;
    void setWind(WeatherStation wind);
    void setTimer(Timer timer);
    ShipModel getResult();
}
