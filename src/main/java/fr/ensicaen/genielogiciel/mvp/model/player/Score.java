package fr.ensicaen.genielogiciel.mvp.model.player;

import java.util.ArrayList;
import java.lang.String;

public class Score {
    private ArrayList<String> _score = new ArrayList<>();

    public ArrayList<String> getScores() {
        return _score;
    }

    public void addScore(int time_in_milliseconds) {
        int h = time_in_milliseconds % 3600000;
        int m = (time_in_milliseconds - h) % 60000;
        int s = (time_in_milliseconds - m) % 1000;
        int ms =  time_in_milliseconds - s;
        _score.add(h+":"+m+":"+s+":"+ms);
    }

}
