package fr.ensicaen.genielogiciel.mvp.model.sail;

public class LargeSailDecorator extends SailDecorator{
    public LargeSailDecorator(Sail decoratedSail) {
        super(decoratedSail);
    }

    @Override
    public double getSpeedRotation() {
        return super.getSpeedRotation();
    }

    @Override
    public double getShipSpeed(double angle) {
        return super.getShipSpeed(angle)+(angle >= -60 && angle <= 60?((60-Math.abs(angle))/20.0):0);
    }
}
