package fr.ensicaen.regate.mvp.model.player;

import fr.ensicaen.regate.mvp.model.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class Score {

    private final ArrayList<String> _score = new ArrayList<>();

    public List<String> getScores() {
        return _score;
    }

    public String getScore(int index) throws IndexOutOfBoundsException {
        return _score.get(index);
    }

    public void addScore(int timeInMilliseconds) {
        int h = timeInMilliseconds / 3600000;
        timeInMilliseconds = timeInMilliseconds - h*3600000;
        int m = timeInMilliseconds / 60000;
        timeInMilliseconds = timeInMilliseconds - m*60000;
        int s = timeInMilliseconds / 1000;
        int ms =  timeInMilliseconds - s*1000;
        _score.add(String.format("%02d:%02d:%03d", m, s, ms));
    }

    public void registerScore() {
        Stopwatch stopwatch = Stopwatch.getInstance();
        addScore(stopwatch.getTime());
    }
}
