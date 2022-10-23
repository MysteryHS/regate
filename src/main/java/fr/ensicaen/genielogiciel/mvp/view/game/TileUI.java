package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Sand;
import fr.ensicaen.genielogiciel.mvp.model.map.Tile;
import fr.ensicaen.genielogiciel.mvp.model.map.Water;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TileUI extends ImageView {
    private Tile _tile;


    public TileUI(Tile tile) {
        _tile = tile;
        if(tile instanceof Sand) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/sand.png"));
        } else if(tile instanceof Water) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/water.png"));
        } else {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/water.png"));
        }

    }

    public void draw(AnchorPane pane,double caseWidthInPixel, double caseHeightInPixel) {

        setFitWidth(caseWidthInPixel);
        setFitHeight(caseHeightInPixel);
        setLayoutX(caseWidthInPixel * _tile.getCoordinateX());
        setLayoutY(caseHeightInPixel * _tile.getCoordinateY());
        pane.getChildren().add(this);
    }
}
