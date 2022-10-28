package fr.ensicaen.genielogiciel.mvp.model.ship.command;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

import java.util.Date;

public class MoveRight extends Move {
    public MoveRight(ShipModel ship, long delay){
        super(ship, delay);
    }
    @Override
    public void execute() {
        execute(2);
    }
    public String toString(){
        return super.toString() + " RIGHT";
    }
}
