package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Sand;
import fr.ensicaen.genielogiciel.mvp.model.map.Tile;
import fr.ensicaen.genielogiciel.mvp.model.map.Water;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TileView extends ImageView {
    private Tile _tile;

    private int _x;
    private int _y;
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;


    public TileView(Tile tile,double caseWidthInPixel, double caseHeightInPixel, int Xcoordinate, int Ycoordinate) {
        //TODO decoupler
        _tile = tile;

        _x = Xcoordinate;
        _y = Ycoordinate;
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;

        if(tile instanceof Sand) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/sand.png"));
        } else if(tile instanceof Water) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/water.png"));
        } else {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/water.png"));
        }
    }

    public void draw(AnchorPane pane) {
        setFitWidth(_caseWidthInPixel);
        setFitHeight(_caseHeightInPixel);

        setLayoutX(_caseWidthInPixel * _x);
        setLayoutY(_caseHeightInPixel * _y);

        pane.getChildren().add(this);
    }
}
