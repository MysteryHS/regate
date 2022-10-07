package fr.ensicaen.genielogiciel.mvp.model.player;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public abstract class Player {
    private Score score;
    private final String _name;

    private final ShipModel _ship;

    Player(String name, ShipModel ship) {
        _ship = ship;
        _name = name;
    }

    public ShipModel getShip(){
        return _ship;
    }

    public String getName(){
        return _name;
    }
}
