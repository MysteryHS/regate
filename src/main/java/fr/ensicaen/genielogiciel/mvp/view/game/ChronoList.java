package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.layout.AnchorPane;

public class ChronoList {

    private int _nbItems;
    private AnchorPane _pane;

    public ChronoList(AnchorPane pane) {
        _pane = pane;
        _nbItems = 0;
    }

    public void addChrono(ChronoItem item) {
        int posX = 80;
        int posY = 80+_nbItems*20;
        item.draw(_pane,posX,posY);
        _nbItems++;
    }

}
