package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoatView extends ImageView {
    GameView view;
    ShipModel _shipModel;


    public BoatView(GameView view, ShipModel shipModel) {
        this.view = view;
        this._shipModel = shipModel;

        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boat.png"));

    }

    public void draw(AnchorPane pane) {
        this.setLayoutX(_shipModel.getX());
        this.setLayoutY(_shipModel.getY());

        this.setFitWidth(17);
        this.setFitHeight(22);


        pane.getChildren().add(this);
    }


    public void move(double dx, double dy) {
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);
    }


    public void rotate(double val) {
        setRotate(val);
    }

}
