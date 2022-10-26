package fr.ensicaen.genielogiciel.mvp.view.game;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MapView {
    private ArrayList<TileView> _tiles;

    private ArrayList<BuoyView> _buoys;

    public static int _mapWidthInPixel = 500;
    public static int _mapHeightInPixel = 500;
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;

    private int _mapWidth;
    private int _mapHeight;




    public MapView(double caseWidthInPixel, double caseHeightInPixel, int mapWidth, int mapHeight) {
        _tiles = new ArrayList<TileView>();
        _buoys = new ArrayList<BuoyView>();

        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;

        _mapWidth = mapWidth;
        _mapHeight = mapHeight;
    }

    public void addTile(TileView tile) {
        _tiles.add(tile);
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
