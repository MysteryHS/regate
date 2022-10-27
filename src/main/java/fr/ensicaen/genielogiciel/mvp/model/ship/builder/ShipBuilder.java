package fr.ensicaen.genielogiciel.mvp.model.ship.builder;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;

public interface ShipBuilder {

    void setPosition(double x, double y);
    void setSail(Sail sail );
    void setCrew(Crew crew );
    void setPolar(String polarNameFromResourcePackage ) throws FileNotFoundException;
    void setWind(WeatherStation wind );
    ShipModel getResult();
}
