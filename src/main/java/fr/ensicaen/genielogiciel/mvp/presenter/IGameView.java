package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public interface IGameView {
    public void draw(GameMap mapModel, Player playerModel);

    public void update(Player playerModel);
}
