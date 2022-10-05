package fr.ensicaen.genielogiciel.mvp.presenter;

public interface IGameView {
    void addBoat( double x, double y );
    public void drawMap();

    void update( double dx, double dy, double angle );
}
