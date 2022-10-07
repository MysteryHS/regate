package fr.ensicaen.genielogiciel.mvp.model.player;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.String;

public class Score {

    private ArrayList<String> _score = new ArrayList<>();

    public ArrayList<String> getScores() {
        return _score;
    }

    public String getScore(int index) throws IOException {
        if (_score.size() <= 0 || index >= _score.size()) {
            throw new IOException("Error");
        }
        return _score.get(index);
    }

    public void addScore(int time_in_milliseconds) {
        int h = time_in_milliseconds / 3600000;
        time_in_milliseconds = time_in_milliseconds - h*3600000;
        int m = time_in_milliseconds / 60000;
        time_in_milliseconds = time_in_milliseconds - m*60000;
        int s = time_in_milliseconds / 1000;
        int ms =  time_in_milliseconds - s*1000;
        _score.add(h+":"+m+":"+s+":"+ms);
    }


}
