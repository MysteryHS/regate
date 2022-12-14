package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoatView extends ImageView {
    private GameView _view;
    private ShipModel _shipModel;
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;




    public BoatView(GameView view, ShipModel shipModel, int caseWidthInPixel, int caseHeightInPixel) {
        _view = view;
        _shipModel = shipModel;

        setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boat.png"));

        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
    }

    public void draw(AnchorPane pane) {

        this.setLayoutX(_shipModel.getX()*_caseWidthInPixel);
        this.setLayoutY(_shipModel.getY()*_caseHeightInPixel);

        this.setFitWidth(24);
        this.setFitHeight(33);


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
