package fr.ensicaen.regate.mvp.view.game;

import fr.ensicaen.regate.mvp.model.map.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TileView extends ImageView {
    private final int _x;
    private final int _y;
    private final double _caseHeightInPixel;
    private final double _caseWidthInPixel;

    public TileView(Tile tile,double caseWidthInPixel, double caseHeightInPixel, int xCoordinate, int yCoordinate) {
        _x = xCoordinate;
        _y = yCoordinate;
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
        setImage(new Image(tile.getImageSRC()));
    }

    public void draw(AnchorPane pane) {
        setFitWidth(_caseWidthInPixel);
        setFitHeight(_caseHeightInPixel);
        setLayoutX(_caseWidthInPixel * _x);
        setLayoutY(_caseHeightInPixel * _y);
        pane.getChildren().add(this);
    }
}
