package fr.ensicaen.genielogiciel.mvp.model;

public class Chrono {
    private static Chrono INSTANCE = null;
    private static int _referenceTime;

    private Chrono() {
        _referenceTime = (int) System.currentTimeMillis();
    }

    public static Chrono getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Chrono();
        }
        return INSTANCE;
    }

    public int getTime() {
        int currentTime = (int) System.currentTimeMillis();
        return (currentTime - _referenceTime);
    }

    public void restartReferenceTime() {
        _referenceTime = (int) System.currentTimeMillis();
    }

}

