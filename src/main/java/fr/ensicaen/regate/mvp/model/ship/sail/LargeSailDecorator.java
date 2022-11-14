package fr.ensicaen.regate.mvp.model.ship.sail;

public class LargeSailDecorator extends SailDecorator{
    public LargeSailDecorator(Sail decoratedSail) {
        super(decoratedSail);
    }

    @Override
    public double getSpeedRotation() {
        return 0.8;
    }

    @Override
    public double getShipSpeed(double angle) {
        return super.getShipSpeed(angle)+(angle >= -60 && angle <= 60?((60-Math.abs(angle))/300.0):0);
    }
}
