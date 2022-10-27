package fr.ensicaen.genielogiciel.mvp.model.ship.builder;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.LargeSailDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;

import java.io.FileNotFoundException;

public class ShipDirector {

    private final ShipBuilder _builder;

    public ShipDirector(ShipBuilder builder) {
        _builder = builder;
    }

    public ShipDirector buildFigaro() throws FileNotFoundException {
        _builder.setPolar("polaire-figaro.pol");
        return this;
    }

    public ShipDirector buildOceanis37() throws FileNotFoundException {
        _builder.setPolar("polaire-oceanis-37.pol");
        return this;
    }

    public ShipDirector buildLargerSail() {
        _builder.setSail(new LargeSailDecorator(new NormalSail()));
        return this;
    }

    public ShipDirector buildNormalSail() {
        _builder.setSail(new NormalSail());
        return this;
    }

    public ShipDirector buildMaxCrew() {
        _builder.setCrew(new MaxCrewDecorator(new NormalCrew()));
        return this;
    }

    public ShipDirector buildNormalCrew() {
        _builder.setCrew(new NormalCrew());
        return this;
    }
    


    public ShipDirector addWind(WeatherStation wind) {
        _builder.setWind(wind);
        return this;
    }

    public ShipModel build() {
        return _builder.getResult();
    }
}
