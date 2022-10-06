package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import javafx.scene.layout.AnchorPane;

public class MapView {
    private Map _mapModel;
    private GameView _view;

    public static int mapHeight = 500;
    public static int mapWidth = 500;

    public MapView(GameView view, Map mapModel) {
        this._mapModel = mapModel;
        this._view = view;
    }


    public void drawMap() {
        int nbCol = 20;
        int nbRow = 20;
        double sizeWidth = (mapWidth / _mapModel.getWidth());
        double sizeHeight = (mapHeight / _mapModel.getHeight());

        for (int col = 0; col < _mapModel.getWidth(); col++) {
            for (int row = 0; row < _mapModel.getHeight(); row++) {
                TileUI tile = new TileUI(_mapModel.getTile(col,row));
                tile.setFitWidth(sizeWidth);
                tile.setFitHeight(sizeHeight);
                tile.setLayoutX(sizeWidth * col);
                tile.setLayoutY(sizeHeight * row);
                _view.getMapPane().getChildren().add(tile);
            }
        }
    }

}
