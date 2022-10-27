package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;

public class PassedBuoy {
    private final Player _player;
    private final GameMap _map;
    private int _indexBuoyToPass = 0;

    public PassedBuoy(Player player, GameMap map) {
        _player = player;
        _map = map;
    }

    public void detectionPassageBuoy(){
        System.out.println(_indexBuoyToPass);
        if (_map.isPassingBuoyNumber(_indexBuoyToPass,
                (int) _player.getShip().getX(), (int) _player.getShip().getY())){
            System.out.println("boué nb: ");

            _indexBuoyToPass++;
            _player.addScore();
        }
    }
}
