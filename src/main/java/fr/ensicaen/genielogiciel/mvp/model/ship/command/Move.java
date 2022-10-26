package fr.ensicaen.genielogiciel.mvp.model.ship.command;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

public abstract class Move implements Command{

    private ShipModel _ship;
    private long _delay;

    public Move(ShipModel ship, long delay){
        _ship = ship;
        _delay = delay;
    }

    public void execute(int angle){
        _ship.rotate(angle);
    }

    @Override
    public long getDelay(){
        return _delay;
    }

    @Override
    public String toString(){
        return Long.toString(_delay);
    }
}
