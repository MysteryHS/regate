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
    public boolean detectionPassageBuoy(){
        int shipX = (int) _player.getShip().getX();
        int shipY = (int) _player.getShip().getY();
        if (_map.isPassingBuoyNumber(_indexBuoyToPass, shipX, shipY)){
            _indexBuoyToPass++;
            _player.addScore();
            return true;
        }
        return false;
    }

    public int getNextBuoyIndexInList() {
        return _indexBuoyToPass;
    }
}

