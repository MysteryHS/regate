package fr.ensicaen.genielogiciel.mvp.model.sail;

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
