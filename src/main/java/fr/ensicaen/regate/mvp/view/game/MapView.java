package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MapView {
    private final ArrayList<TileView> _tiles;

    private final ArrayList<BuoyView> _buoys;

    public static final int _mapWidthInPixel = 500;
    public static final int _mapHeightInPixel = 500;


    public MapView() {
        _tiles = new ArrayList<>();
        _buoys = new ArrayList<>();

    }
    public void isNextBuoy(int index) {
        for(BuoyView buoy : _buoys) {
            buoy.passed();
        }
        if(index>=_buoys.size()) {
            return;
        }
        _buoys.get(index).isNext();
    }

    public void addTile(TileView tile) {
        _tiles.add(tile);
    }
    public void addBuoy(BuoyView buoy) {
        _buoys.add(buoy);
    }
    public void draw(AnchorPane pane) {
        pane.resize(_mapWidthInPixel,_mapHeightInPixel);
        for(TileView tile : _tiles) {
            tile.draw(pane);
        }
        for(BuoyView buoy : _buoys) {
            buoy.draw(pane);
        }
    }
}
