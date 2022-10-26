package fr.ensicaen.genielogiciel.mvp.model.player;

import java.io.IOException;
import java.util.List;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public abstract class Player {
    private Score _score;
    private final String _name;

    private final ShipModel _ship;

    Player(String name, ShipModel ship) {
        _ship = ship;
        _name = name;
        _score = new Score();
    }

    public List<String> getScores() {
        return _score.getScores();
    }

    public String getScore(int index) throws IOException {
        return _score.getScore(index);
    }

    public ShipModel getShip(){
        return _ship;
    }

    public String getName(){
        return _name;
    }
}

