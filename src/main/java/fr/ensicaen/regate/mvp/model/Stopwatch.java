package fr.ensicaen.regate.mvp.model;

public class Stopwatch {
    private static Stopwatch INSTANCE = null;
    private static int _referenceTime;

    private Stopwatch() {
        _referenceTime = (int) System.currentTimeMillis();
    }

    public static Stopwatch getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Stopwatch();
        }
        return INSTANCE;
    }

    public int getTime() {
        int currentTime = (int) System.currentTimeMillis();
        return (currentTime - _referenceTime);
    }

    public String getStringFormatStopwatch() {
        int timeInMilliseconds = this.getTime();
        int h = timeInMilliseconds / 3600000;
        timeInMilliseconds = timeInMilliseconds - h*3600000;
        int m = timeInMilliseconds / 60000;
        timeInMilliseconds = timeInMilliseconds - m*60000;
        int s = timeInMilliseconds / 1000;
        return String.format("%02d:%02d", m, s);
    }

    public void restartReferenceTime() {
        _referenceTime = (int) System.currentTimeMillis();
    }
}

