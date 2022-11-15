package fr.ensicaen.regate.mvp.model.player;

import fr.ensicaen.regate.mvp.model.ship.ShipModel;

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
        } catch (IndexOutOfBoundsException e) {
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