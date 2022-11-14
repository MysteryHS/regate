package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.layout.AnchorPane;

public class StopwatchList {
    private int _nbItems;
    private final AnchorPane _pane;

    public StopwatchList(AnchorPane pane) {
        _pane = pane;
        _nbItems = 0;
    }

    public void addStopwatchItem(StopwatchItem item) {
        int posX = 80;
        int posY = 80+_nbItems*20;
        item.draw(_pane,posX,posY);
        _nbItems++;
    }

}
