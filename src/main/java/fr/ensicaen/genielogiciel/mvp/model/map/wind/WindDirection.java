package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public enum WindDirection {
    SOUTH           (180),
    SOUTH_EAST      (225),
    EAST            (270),
    NORTH_EAST      (315),
    NORTH           (0),
    NORTH_WEST      (45),
    WEST            (90),
    SOUTH_WEST      (135);

    private final int _angle;
    WindDirection(int angle) {
        _angle = angle;
    }

    public int getAngle() {
        return _angle;
    }
}
