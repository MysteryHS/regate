package fr.ensicaen.genielogiciel.mvp.model.player;

import java.util.ArrayList;
import java.lang.String;

public class Score {
    private ArrayList<String> _score = new ArrayList<>();

    public ArrayList<String> getScores() {
        return _score;
    }

    public void addScore(int time_in_seconds) {
        int h = time_in_seconds % 3600;
        int m = (time_in_seconds - h) % 60;
        int s = time_in_seconds - m;

        _score.add(h+":"+m+":"+s);
    }

}
