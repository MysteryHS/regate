package fr.ensicaen.genielogiciel.mvp.model.player;

public abstract class Player {
    private Score score;
    private String _name;

    Player(String name) {
        _name = name;
    }
}
