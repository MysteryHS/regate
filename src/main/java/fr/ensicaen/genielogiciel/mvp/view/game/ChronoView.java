package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ChronoView extends Text {

    public ChronoView() {}


    public void draw(AnchorPane pane) {
        setLayoutX(95);
        setLayoutY(425);
        this.setStyle("-fx-font-size: 25px;-fx-text-inner-color: white;");
        setText("00-00-00");


        pane.getChildren().add(this);
    }

    public void refresh(String text) {
        setText(text);
    }

}
