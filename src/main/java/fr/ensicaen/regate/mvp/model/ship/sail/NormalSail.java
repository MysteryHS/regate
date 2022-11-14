package fr.ensicaen.regate.mvp.model.ship.sail;

public class NormalSail implements Sail{

    @Override
    public double getSpeedRotation() {
        return 1;
    }

    @Override
    public double getShipSpeed(double angle) {
        return 1;
    }
}
