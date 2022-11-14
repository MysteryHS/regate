package fr.ensicaen.regate.mvp.presenter;


import fr.ensicaen.regate.mvp.view.game.*;

public interface IGameView {

    void draw(double boatPosX, double boatPosY,String windDirection,double windKnot);

    void initView(MapView map, ShipView ship, WindView wind);

    void update(double angle, double dx,double dy,String stopwatch,int indexInListNextBuoy);

    void addBuoyPassedToDisplayedList(String stopwatch);
}
