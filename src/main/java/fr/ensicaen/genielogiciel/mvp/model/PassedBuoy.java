package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;

public class PassedBuoy {
    private final Player _player;
    private final Map _map;
    private int _indexBuoyToPass = 0;

    public PassedBuoy(Player player, Map map) {
        _player = player;
        _map = map;
    }

    public void detectionPassageBuoy(){
        if (_map.isPassingBuoyNumber(_indexBuoyToPass,
                (int) _player.getShip().getX(), (int) _player.getShip().getY())){
            _indexBuoyToPass++;
            // TODO : Ajouter un score au joueur
        }
    }
}
