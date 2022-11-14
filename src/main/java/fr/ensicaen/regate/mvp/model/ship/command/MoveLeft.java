package fr.ensicaen.regate.mvp.model.ship.command;

import fr.ensicaen.regate.mvp.model.ship.ShipModel;

public class MoveLeft extends Move {

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
