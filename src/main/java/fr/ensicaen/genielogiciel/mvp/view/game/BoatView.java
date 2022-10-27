package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel; // FIXME couplage entre vue et modèle !!!!
                                                           // FIXME et donc necessite alors des accesseurs !!!
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoatView extends ImageView {
    final private GameView _view;
    final private ShipModel _shipModel;
    final private double _caseHeightInPixel;
    final private double _caseWidthInPixel;
    final private double _shipHeight = 33.0;
    final private double _shipWidth = 24.0;

    public BoatView(GameView view, ShipModel shipModel, int caseWidthInPixel, int caseHeightInPixel) {
        _view = view;
        _shipModel = shipModel;
        setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boat.png"));
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
        setFitWidth(_shipWidth);
        setFitHeight(_shipHeight);
        _shipModel.setHeight(_shipHeight/_caseHeightInPixel);
        _shipModel.setWidth(_shipWidth/_caseWidthInPixel);
    }

    public void draw(AnchorPane pane) {
        move(_shipModel.getX(), _shipModel.getY());
        pane.getChildren().add(this);
    }


    public void move(double x, double y) {
        setLayoutX(x*_caseWidthInPixel);
        setLayoutY(y*_caseHeightInPixel);
    }


    public void rotate(double val) {
        setRotate(val);
    }

}
