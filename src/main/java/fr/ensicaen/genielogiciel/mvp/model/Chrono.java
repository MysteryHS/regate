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

    public String getFormateChrono() {
        int timeInMilliseconds = this.getTime();
        int h = timeInMilliseconds / 3600000;
        timeInMilliseconds = timeInMilliseconds - h*3600000;
        int m = timeInMilliseconds / 60000;
        timeInMilliseconds = timeInMilliseconds - m*60000;
        int s = timeInMilliseconds / 1000;

        String sM;
        if(m==0) {
            sM = "00";
        } else if(m<9) {
            sM = "0"+m;
        } else {
            sM = m+"";
        }

        String sS;
        if(s==0) {
            sS = "00";
        } else if(s<9) {
            sS = "0"+s;
        } else {
            sS = s+"";
        }

        return sM+":"+sS;
    }

    public void restartReferenceTime() {
        _referenceTime = (int) System.currentTimeMillis();
    }

}

