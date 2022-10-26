package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Buoy; // FIXME couplage entre vue et modèle !!!!
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MapView {
    private GameMap _mapModel; // FIXME tous ces attributs sont final
    private GameView _view;
    private ArrayList<TileUI> _tilesUI;

    private ArrayList<BuoyView> _buoysView;


    private double _caseHeightInPixel;
    private double _caseWidthInPixel;




    public MapView(GameView view, GameMap mapModel, int caseWidthInPixel, int caseHeightInPixel) {
        _mapModel = mapModel;
        _view = view;
        _tilesUI = new ArrayList<TileUI>(); // FIXME inutle utilser la déclation diamant
        _buoysView = new ArrayList<BuoyView>();

        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
    }


    public void draw(AnchorPane pane) {
        pane.resize(_mapModel.getWidth(),_mapModel.getHeight());
        for (int col = 0; col < _mapModel.getWidth(); col++) {
            for (int row = 0; row < _mapModel.getHeight(); row++) {
                TileUI tile = new TileUI(_mapModel.getTile(col,row));
                tile.draw(pane,_caseWidthInPixel,_caseHeightInPixel);
                _tilesUI.add(tile);
            }
        }

        for(Buoy buoy : _mapModel.getBuoys()) {
            BuoyView buoyView = new BuoyView(_view,buoy);
            buoyView.draw(pane,_mapModel.getWidth(),_mapModel.getHeight());
            this._buoysView.add(buoyView); // FIXME code debutant
        }
    }

}
