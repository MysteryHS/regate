package fr.ensicaen.regate.mvp.model.map.wind;

public enum WindDirection {
    SOUTH           (0),
    SOUTH_EAST      (45),
    EAST            (90),
    NORTH_EAST      (135),
    NORTH           (180),
    NORTH_WEST      (225),
    WEST            (270),
    SOUTH_WEST      (315);

    private final double _angle;
    WindDirection(double angle) {
        _angle = angle;
    }

    public double getAngle() {
        return _angle;
    }
}
