package fr.ensicaen.genielogiciel.mvp.model.ship.builder;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.io.FileNotFoundException;

public class ConcreteShipBuilder implements ShipBuilder {
    private Sail _sail;
    private Crew _crew;
    private DataPolar _polar;
    private WeatherStation _wind;
    @Override
    public ShipBuilder setSail(Sail sail) {
        _sail = sail;
        return this;
    }

    @Override
    public ShipBuilder setCrew(Crew crew) {
        _crew = crew;
        return this;
    }

    @Override
    public void setPolar(String polarNameFromResourcePackage) throws FileNotFoundException {
        _polar = new DataPolar( polarNameFromResourcePackage );
    }

    @Override
    public ShipBuilder setWind(WeatherStation wind) {
        _wind = wind;
        return this;
    }

    @Override
    public ShipModel getResult() {
        return new ShipModel( _sail, _crew, _wind, _polar );
    }
}
