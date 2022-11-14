package fr.ensicaen.regate.mvp.model.ship.command;

import fr.ensicaen.regate.mvp.model.ship.ShipModel;

public abstract class Move implements Command{

    private final ShipModel _ship;
    private final long _delay;

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
