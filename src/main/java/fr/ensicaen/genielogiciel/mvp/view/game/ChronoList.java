package fr.ensicaen.genielogiciel.mvp.view.game;

import java.util.ArrayList;

public class ChronoList {

    private int nbItems;

    public ChronoList() {
        nbItems = 0;
    }

    public void addChrono(ChronoItem item) {
        int posX = 0;
        int posY = 0;
        item.draw(posX,posY);
        nbItems++;
    }

}
