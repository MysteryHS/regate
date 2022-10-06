package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoatView extends ImageView {
    GameView view;
    BoatModel _boatModel;


    public BoatView(GameView view, BoatModel boatModel) {
        this.view = view;
        this._boatModel = boatModel;

        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boat.png"));

    }

    public void draw(AnchorPane pane) {
        this.setLayoutX(_boatModel.getX());
        this.setLayoutY(_boatModel.getY());

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
