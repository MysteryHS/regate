package fr.ensicaen.genielogiciel.mvp.model.player;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

import java.io.IOException;

public abstract class Player {
    private final Score _score;
    private final ShipModel _ship;

    Player(ShipModel ship) {
        _ship = ship;
        _score = new Score();
    }

    public String getLatestScore() {
        try {
            return _score.getScore(_score.getScores().size()-1);
        } catch (IOException e) {
            return "";
        }
    }

    public void addScore(){
        _score.registerScore();
    }

    public ShipModel getShip(){
        return _ship;
    }
}