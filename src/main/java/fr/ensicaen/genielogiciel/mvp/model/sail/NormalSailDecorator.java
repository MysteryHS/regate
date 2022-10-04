package fr.ensicaen.genielogiciel.mvp.model.sail;

public class NormalSailDecorator extends SailDecorator{

    public NormalSailDecorator(Sail decoratedSail) {
        super(decoratedSail);
    }

    @Override
    public double getSpeedRotation() {
        return super.getSpeedRotation()+1;
    }

    @Override
    public double getShipSpeed(double angle) {
        return super.getShipSpeed(angle)+1+(angle >= -60 && angle <= 60?((60-Math.abs(angle))/30.0):0);
    }
}
