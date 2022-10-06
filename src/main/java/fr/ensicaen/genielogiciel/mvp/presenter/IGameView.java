package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;

public interface IGameView {
    public void draw(Map mapModel, BoatModel boatModel);

    public void update(BoatModel boatModel, PlayerModel playerModel);
}
