package fr.ensicaen.genielogiciel.mvp.model.player;

import fr.ensicaen.genielogiciel.mvp.model.Chrono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Score {

    private final ArrayList<String> _score = new ArrayList<>();

    public List<String> getScores() {
        return _score;
    }

    public String getScore(int index) throws IOException {
        if (_score.size() <= 0 || index >= _score.size()) {
            throw new IOException("Error wrong size");
        }
        return _score.get(index);
    }

    public void addScore(int timeInMilliseconds) {
        int h = timeInMilliseconds / 3600000;
        timeInMilliseconds = timeInMilliseconds - h*3600000;
        int m = timeInMilliseconds / 60000;
        timeInMilliseconds = timeInMilliseconds - m*60000;
        int s = timeInMilliseconds / 1000;
        int ms =  timeInMilliseconds - s*1000;
        _score.add(h+":"+m+":"+s+":"+ms);
    }

    public void registerScore() {
        Chrono chrono = Chrono.getInstance();
        addScore(chrono.getTime());
    }

    public void resetScore() {
        _score = new ArrayList<>();
        Chrono chrono = Chrono.getInstance();
        chrono.restartReferenceTime();
    }



    public int getSizeOfTheScore() {
        return _score.size();
    }
}
