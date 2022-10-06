package fr.ensicaen.genielogiciel.mvp.model.map.wind;

public enum WindDirection {
    EAST            (0),
    NORTH_EAST      (45),
    NORTH           (90),
    NORTH_WEST      (135),
    WEST            (180),
    SOUTH_WEST      (225),
    SOUTH           (270),
    SOUTH_EAST      (315);

    private final double _angle;
    WindDirection(double angle) {
        _angle = angle;
    }

    double getAngle() {
        return _angle;
    }
}
