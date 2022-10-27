package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.view.game.MapView;
import fr.ensicaen.genielogiciel.mvp.view.game.ShipView;
import fr.ensicaen.genielogiciel.mvp.view.game.WindView;

public interface IGameView {
    public void draw(double boatPosX, double boatPosY,String windDirection,double windKnot);

    public void initView(MapView map, ShipView ship, WindView wind);

    public void update(double angle, double dx,double dy,String chrono);

    public void addBuoyPassedToDisplayedList(String chrono);
}
