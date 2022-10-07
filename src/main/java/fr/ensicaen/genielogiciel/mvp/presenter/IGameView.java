package fr.ensicaen.genielogiciel.mvp.presenter;

public interface IGameView {
    void addShip(double x, double y );

    void update( double dx, double dy, double angle );
}
