package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.player.User;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.Crew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.Sail;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Player> _players = new ArrayList<>();
    Map _map;

    public Model(Map map){
        _map = map;
    }

    public void createPlayer(String nickname, Sail sail, Crew crew, String polarName){
        ShipModel model = new ShipModel(sail, crew, _map.getWind(), polarName);
        Player player = new User(nickname, model);
        _players.add(player);
    }

    public Player getFirstPlayer(){
        return _players.get(0);
    }

    public Map getMap(){
        return _map;
    }

}
