package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class PlayerModel {
    private String _nickname;

    private final ShipModel _ship;

    public PlayerModel(String nickname, ShipModel ship){
        _nickname = nickname;
        _ship = ship;
    }

    public String getNickname() {
        return _nickname;
    }

    public void setNickname( String nickname ) {
        _nickname = nickname;
    }
}
