package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class StopwatchView extends Text {

    public StopwatchView() {}

    public void draw(AnchorPane pane) {
        setLayoutX(110);
        setLayoutY(425);
        this.setStyle("-fx-font-size: 25px;-fx-text-inner-color: white;");
        setText("00:00");
        pane.getChildren().add(this);
    }

    public void refresh(String text) {
        setText(text);
    }
}
