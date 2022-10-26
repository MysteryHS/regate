package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ShipView extends ImageView {
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;




    public ShipView(double caseWidthInPixel, double caseHeightInPixel) {
        setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/boats/boat.png"));

        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
    }

    public void draw(AnchorPane pane, double dx, double dy) {
        this.setLayoutX(dx*_caseWidthInPixel);
        this.setLayoutY(dy*_caseHeightInPixel);


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
