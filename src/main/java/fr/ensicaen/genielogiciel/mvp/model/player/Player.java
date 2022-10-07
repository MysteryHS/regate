package fr.ensicaen.genielogiciel.mvp.model.player;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Player {
    private Score _score;
    private final String _name;

    Player(String name) {
        _name = name;
        _score = new Score();
    }

    public ArrayList<String> getScores() {
        return _score.getScores();
    }

    public String getScore(int index) throws IOException {
        return _score.getScore(index);
    }

    public String getName() {
        return _name;
    }
}

