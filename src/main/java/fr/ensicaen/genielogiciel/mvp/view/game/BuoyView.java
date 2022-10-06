package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.model.map.Buoy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BuoyView extends ImageView {
    GameView _view;
    Buoy _buoy;

    public static int mapHeight = 500;
    public static int mapWidth = 500;


    public BuoyView(GameView view, Buoy buoy) {
        this._view = view;
        this._buoy = buoy;

        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/flag.png"));

    }

    public void draw(AnchorPane pane,int nbColumns, int nbRows) {
        double sizeWidth = (mapWidth / nbColumns);
        double sizeHeight = (mapHeight / nbRows);
        this.setLayoutX(sizeWidth*_buoy.getX());
        this.setLayoutY(sizeHeight*_buoy.getY());

        this.setFitWidth(10);
        this.setFitHeight(10);


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
