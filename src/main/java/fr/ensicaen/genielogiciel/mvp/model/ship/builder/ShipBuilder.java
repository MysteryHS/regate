package fr.ensicaen.genielogiciel.mvp.model.ship.builder;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;

public interface ShipBuilder {
    ShipBuilder setSail( Sail sail );
    ShipBuilder setCrew( Crew crew );
    void setPolar(String polarNameFromResourcePackage ) throws FileNotFoundException;
    ShipBuilder setWind( WeatherStation wind );
    ShipModel getResult();
}
