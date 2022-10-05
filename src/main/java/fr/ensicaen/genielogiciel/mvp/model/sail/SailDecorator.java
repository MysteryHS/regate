package fr.ensicaen.genielogiciel.mvp.model.sail;

public abstract  class SailDecorator implements Sail {
    private final Sail _decoratedSail;
    public SailDecorator(Sail decoratedSail){
        _decoratedSail = decoratedSail;
    }
    @Override
    public double getSpeedRotation(){
        return _decoratedSail.getSpeedRotation();
    }

    @Override
    public double getShipSpeed(double angle){
        return _decoratedSail.getShipSpeed(angle);
    }
}
