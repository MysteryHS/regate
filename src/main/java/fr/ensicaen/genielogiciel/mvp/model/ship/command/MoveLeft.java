package fr.ensicaen.genielogiciel.mvp.model.ship.command;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

import java.util.Date;
import java.util.Timer;

public class MoveLeft extends Move {
    private ShipModel _ship;
    private long _delay;

    public MoveLeft(ShipModel ship, long delay){
       super(ship, delay);
    }

    @Override
    public void execute() {
        execute(-2);
    }

    @Override
    public String toString(){
        return super.toString() + " LEFT";
    }
}
