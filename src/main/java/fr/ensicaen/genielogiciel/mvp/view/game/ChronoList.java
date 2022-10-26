package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ChronoList {

    private int _nbItems;
    private AnchorPane _pane;

    public ChronoList(AnchorPane pane) {
        _pane = pane;
        _nbItems = 0;
    }

    public void addChrono(ChronoItem item) {
        int posX = 30;
        int posY = 30+_nbItems*30;
        item.draw(_pane,posX,posY);
        _nbItems++;
    }

}
