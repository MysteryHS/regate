package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public interface IGameView {
    public void draw(Map mapModel, Player playerModel);

    public void update(Player playerModel);
}
