package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public class Collision {
    final private GameMap _map;
    final private ShipModel _ship;

    public Collision(GameMap map, ShipModel ship) {
        _map = map;
        _ship = ship;
    }


    public boolean outOfRange() {
        boolean infX = _ship.getX() + _ship.getDx() < 0;
        boolean supX = _ship.getX() + _ship.getDx() + _ship.getWidth() >= _map.getWidth();
        boolean infY = _ship.getY() + _ship.getDy() < 0;
        boolean supY = _ship.getY() + _ship.getDy() + _ship.getHeight() >= _map.getHeight();
        return infX || supX || infY || supY;
    }

    public boolean collisionWithBuoy() {
        boolean checkX;
        boolean checkY;
        for (int i = 0; i < _map.getNbBuoy(); i++) {
            checkX = _map.getBuoys().get(i).getY() == _ship.getY() + _ship.getDy();
            checkY = _map.getBuoys().get(i).getY() == _ship.getY() + _ship.getDy();
            if (checkX && checkY) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionWithSand() {
        double nextX = _ship.getX() + _ship.getDx();
        double nextY = _ship.getY() + _ship.getDy();
        return _map.getType( (int)nextX, (int)nextY) == '.';
    }

    public void collisionWithOtherPlayers(ShipModel another) {

    }

    private boolean collisionWithSomething() {
        return collisionWithSand() || outOfRange(); //collisionWithBuoy() ||
    }

    public void setMoveShip(){
        _ship.setCollision(collisionWithSomething());
    }
}
