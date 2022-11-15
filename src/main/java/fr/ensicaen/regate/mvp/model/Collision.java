package fr.ensicaen.regate.mvp.model;

import fr.ensicaen.regate.mvp.model.map.GameMap;
import fr.ensicaen.regate.mvp.model.ship.ShipModel;

public class Collision {
    final private GameMap _map;
    final private ShipModel _ship;
    public Collision(GameMap map, ShipModel ship) {
        _map = map;
        _ship = ship;
    }
    public boolean outOfRange() {
        boolean collisionLeft = _ship.getNewPositionLeft() < 0;
        boolean collisionRight = _ship.getNewPositionRight() >= _map.getWidth();
        boolean collisionTop = _ship.getNewPositionTop() < 0;
        boolean collisionBottom = _ship.getNewPositionBottom() >= _map.getHeight();
        return collisionLeft || collisionRight || collisionTop || collisionBottom;
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
        int left = (int)_ship.getNewPositionLeft();
        int right = (int)_ship.getNewPositionRight();
        int top = (int)_ship.getNewPositionTop();
        int bottom = (int)_ship.getNewPositionBottom();
        if(bottom > top){
            bottom = (top + bottom) - (top = bottom);
        }
        if(left > right){
            left = (right + left) - (right = left);
        }
        for(int x = left; x<= right; x++){
            for(int y = bottom; y<= top; y++){
                if(_map.getType(x, y) == '.'){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean collisionWithSomething() {
        return collisionWithSand() || outOfRange(); //collisionWithBuoy() ||
    }
    public void setMoveShip(){
        _ship.setCollision(collisionWithSomething());
    }
}
