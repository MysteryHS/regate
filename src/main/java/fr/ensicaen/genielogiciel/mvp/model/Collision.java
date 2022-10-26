package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class Collision {
    private boolean _collision;
    private Map _map;
    private ShipModel _ship;

    public Collision(Map map, ShipModel ship) {
        _collision = false;
        _map = map;
        _ship = ship;
    }

    private void resetCollision() {
        _collision = false;
    }

    public boolean outOfRange() {
        boolean infX = _ship.getX() + _ship.getDx() < 0;
        boolean supX = _ship.getX() + _ship.getDx() > _map.getWidth();
        boolean infY = _ship.getY() + _ship.getDy() < 0;
        boolean supY = _ship.getY() + _ship.getDy() > _map.getHeight();
        if (infX || supX || infY || supY) {
            _collision = true;
        } else {
            resetCollision();
        }
        return _collision;
    }

    public boolean collisionWithBuoy() {
        boolean checkX;
        boolean checkY;

        for (int i = 0; i < _map.getNbBuoy(); i++) {
            checkX = _map.getBuoys().get(i).getY() == _ship.getY() + _ship.getDy();
            checkY = _map.getBuoys().get(i).getY() == _ship.getY() + _ship.getDy();
            if (checkX && checkY) {
                _collision = true;
            } else {
                resetCollision();
            }
        }
        return _collision;
    }

    public boolean collisionWithSand() {
        double nextX = _ship.getX() + _ship.getDx();
        double nextY = _ship.getY() + _ship.getDy();

        if (_map.getType( (int)nextX, (int)nextY) == '.') {
            _collision = true;
        } else {
            resetCollision();
        }
        return _collision;
    }

    public void collisionWithOtherPlayers(ShipModel another) {

    }
}
