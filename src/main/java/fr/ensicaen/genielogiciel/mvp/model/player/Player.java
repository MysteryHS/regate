package fr.ensicaen.genielogiciel.mvp.model.player;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<String> getScores() {
        return _score.getScores();
    } // FIXME préférer une interface à une implémentation

    public String getScore(int index) throws IOException { // FIXME jamais utilisée
        return _score.getScore(index);
    }

    public ShipModel getShip(){
        return _ship;
    }

    public String getName(){
        return _name;
    }
}

