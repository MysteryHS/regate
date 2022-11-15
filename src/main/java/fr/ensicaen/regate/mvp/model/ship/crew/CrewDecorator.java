package fr.ensicaen.regate.mvp.model.ship.crew;

public abstract  class CrewDecorator implements Crew {
    private final Crew _decoratedCrew;

    public CrewDecorator(Crew decoratedCrew){
        _decoratedCrew = decoratedCrew;
    }

    @Override
    public double getSpeedRotation() {
        return _decoratedCrew.getSpeedRotation();
    }

    @Override
    public double getShipSpeed(double angle){
        return _decoratedCrew.getShipSpeed(angle);
    }
}
