package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
    import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public interface IGameView {
    public void draw(Map mapModel, ShipModel shipModel);

    public void update(ShipModel shipModel, PlayerModel playerModel);
}
