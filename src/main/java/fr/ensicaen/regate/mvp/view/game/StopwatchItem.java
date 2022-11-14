package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class StopwatchItem extends Text {
    private final String _text;

    public StopwatchItem(String text) {
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
