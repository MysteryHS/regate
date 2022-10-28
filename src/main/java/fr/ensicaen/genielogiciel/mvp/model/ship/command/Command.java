package fr.ensicaen.genielogiciel.mvp.model.ship.command;

public interface Command {
    void execute();
    long getDelay();
}
