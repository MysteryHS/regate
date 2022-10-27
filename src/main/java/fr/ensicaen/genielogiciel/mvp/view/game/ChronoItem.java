package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ChronoItem extends Text {

    private String _text;

    public ChronoItem(String text) {
        _text = text;
    }


    public void draw(AnchorPane pane, int posXInPixel, int posYInPixel) {
        setLayoutX(posXInPixel);
        setLayoutY(posYInPixel);
        setText(_text);
        this.setStyle("-fx-font-size: 17px");
        pane.getChildren().add(this);
    }
}
