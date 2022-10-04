package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class BoatUI extends ImageView {
    GameView view;

    public BoatUI(GameView view,double x, double y, double width, double height) {
        this.view = view;
        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boat.png"));
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setFitWidth(width);
        this.setFitHeight(height);
    }


    public void move(double dx, double dy) {
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);
    }


    public void rotate(double val) {
        setRotate(val);
    }

}
