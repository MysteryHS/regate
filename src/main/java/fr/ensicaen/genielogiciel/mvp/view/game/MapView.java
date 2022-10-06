package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Buoy;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MapView {
    private Map _mapModel;
    private GameView _view;
    private ArrayList<TileUI> _tilesUI;

    private ArrayList<BuoyView> _buoysView;

    public static int mapHeight = 500;
    public static int mapWidth = 500;

    public MapView(GameView view, Map mapModel) {
        this._mapModel = mapModel;
        this._view = view;
        _tilesUI = new ArrayList<TileUI>();
        _buoysView = new ArrayList<BuoyView>();
    }


    public void draw(AnchorPane pane) {

        for (int col = 0; col < _mapModel.getWidth(); col++) {
            for (int row = 0; row < _mapModel.getHeight(); row++) {
                TileUI tile = new TileUI(_mapModel.getTile(col,row));
                tile.draw(pane,_mapModel.getWidth(),_mapModel.getHeight());
                _tilesUI.add(tile);
            }
        }

        for(Buoy buoy : _mapModel.getBuoys()) {
            BuoyView buoyView = new BuoyView(_view,buoy);
            buoyView.draw(pane,_mapModel.getWidth(),_mapModel.getHeight());
            this._buoysView.add(buoyView);
        }
    }

}
