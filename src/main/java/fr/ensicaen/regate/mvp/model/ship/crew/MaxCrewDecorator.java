package fr.ensicaen.regate.mvp.model.ship.crew;

public class MaxCrewDecorator extends CrewDecorator {
    public MaxCrewDecorator(Crew decoratedCrew) {
        super(decoratedCrew);
    }

    @Override
    public double getSpeedRotation() {
        return 1.4;
    }

    @Override
    public double getShipSpeed(double angle) {
        return super.getShipSpeed(angle) * 0.8;
    }
}
