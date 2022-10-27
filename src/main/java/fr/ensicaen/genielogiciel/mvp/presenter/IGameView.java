package fr.ensicaen.genielogiciel.mvp.presenter;


import fr.ensicaen.genielogiciel.mvp.view.game.MapView;
import fr.ensicaen.genielogiciel.mvp.view.game.ShipView;
import fr.ensicaen.genielogiciel.mvp.view.game.WindView;

public interface IGameView {

    void draw(double boatPosX, double boatPosY,String windDirection,double windKnot);

    void initView(MapView map, ShipView ship, WindView wind);

    void update(double angle, double dx,double dy,String stopwatch,int indexInListNextBuoy);

    void addBuoyPassedToDisplayedList(String stopwatch);
}
