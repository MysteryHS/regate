package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ShipView extends ImageView {
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;




    public ShipView(String imageSRC,double caseWidthInPixel, double caseHeightInPixel) {
        setImage(new Image(imageSRC));

        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
    }

    public void draw(AnchorPane pane, double dx, double dy) {
        this.setLayoutX(dx*_caseWidthInPixel);
        this.setLayoutY(dy*_caseHeightInPixel);


        this.setFitWidth(_caseWidthInPixel);
        this.setFitHeight(_caseHeightInPixel*1.2);

        pane.getChildren().add(this);
    }


    public void move(double dx, double dy) {
        setLayoutX(getLayoutX() + dx*_caseWidthInPixel);
        setLayoutY(getLayoutY() + dy*_caseHeightInPixel);
    }


    public void rotate(double val) {
        setRotate(val);
    }

}
