package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Sand;
import fr.ensicaen.genielogiciel.mvp.model.map.Tile;
import fr.ensicaen.genielogiciel.mvp.model.map.Water;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileUI extends ImageView {
    private Tile _tile;


    public TileUI(Tile tile) {
        if(tile instanceof Sand) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/sand.png"));
        } else if(tile instanceof Water) {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/water.png"));
        } else {
            this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/water.png"));
        }

    }
}
