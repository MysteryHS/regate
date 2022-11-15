package fr.ensicaen.regate.mvp.model.ship.command;

public interface Command {
    void execute();
    long getDelay();
}
