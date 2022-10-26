package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.text.Text;

public class ChronoItem extends Text {



    public void draw(int posXInPixel,int posYInPixel) {
        setLayoutX(posXInPixel);
        setLayoutY(posYInPixel);
        //setText();
    }
}
