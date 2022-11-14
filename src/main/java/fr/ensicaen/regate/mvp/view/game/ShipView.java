package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ShipView extends ImageView {
    private final double _caseHeightInPixel;
    private final double _caseWidthInPixel;
    public ShipView(String imageSRC,double caseWidthInPixel, double caseHeightInPixel) {
        setImage(new Image(imageSRC));
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
        this.setFitWidth(_caseWidthInPixel);
        this.setFitHeight(_caseHeightInPixel*1.2);
    }
    public void draw(AnchorPane pane, double dx, double dy) {
        this.setLayoutX(dx*_caseWidthInPixel);
        this.setLayoutY(dy*_caseHeightInPixel);
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
