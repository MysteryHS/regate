package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.PassedBuoy;
import fr.ensicaen.genielogiciel.mvp.model.Stopwatch;
import fr.ensicaen.genielogiciel.mvp.model.map.Buoy;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.map.Tile;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.player.User;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ConcreteShipBuilder;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ShipDirector;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeSail;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeShip;
import fr.ensicaen.genielogiciel.mvp.view.game.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class GamePresenter {
    private final Player _playerModel;
    private final Stopwatch _stopwatchModel;
    private final PassedBuoy _passedBuoy;
    private final GameMap _mapModel;
    private IGameView _gameView;
    private boolean _started = false;


    public GamePresenter(GameMap map, TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipModel ship;
        _stopwatchModel = Stopwatch.getInstance();
        _mapModel = map;
        ship = initGame(typeShip, typeSail, typeCrew);
        _playerModel = new User(ship);
        _passedBuoy = new PassedBuoy(_playerModel,_mapModel);
    }

    private ShipModel initGame(TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipDirector director = new ShipDirector(new ConcreteShipBuilder()).buildStartPosition(_mapModel.getStartX(), _mapModel.getStartY());
        if (typeShip == TypeShip.FIGARO37) {
            director.buildFigaro();
        } else {
            director.buildOceanis37();
        }
        if (typeSail == TypeSail.NORMAL_SAIL) {
            director.buildNormalSail();
        } else {
            director.buildLargerSail();
        }
        if (typeCrew == TypeCrew.NORMAL_CREW) {
            director.buildNormalCrew();
        } else {
            director.buildMaxCrew();
        }
        director.addWind(_mapModel.getWind());
        return director.build();
    }

    private void initView() {
        double caseWidthInPixel = MapView._mapWidthInPixel/ (double)_mapModel.getWidth();
        double caseHeightInPixel = MapView._mapHeightInPixel/ (double)_mapModel.getHeight();
        ShipView ship = new ShipView(_playerModel.getShip().getImageSRC(),caseWidthInPixel,caseHeightInPixel);
        WindView wind = new WindView();
        MapView map = new MapView();
        for(Tile tile : _mapModel.getTiles()) {
            map.addTile(new TileView(tile,caseWidthInPixel,caseHeightInPixel, tile.getX(), tile.getY()));
        }
        for(Buoy buoy : _mapModel.getBuoys()) {
            map.addBuoy(new BuoyView(caseWidthInPixel,caseHeightInPixel, buoy.getX(), buoy.getY()));
        }
        _gameView.initView(map,ship,wind);
        _gameView.draw( _playerModel.getShip().getX(),
                        _playerModel.getShip().getY(),
                        _mapModel.getWind().getWindDirection().name(),
                        _mapModel.getWind().getSpeedWindInKnot());
    }

    public void setGameView( IGameView gameView ) {
        _gameView = gameView;
        initView();
    }

    public void handleUserAction( UserAction code ) {
        if (code == UserAction.START) {
            startGame();
        } else {
            changeDirection(code);
        }
    }

    private void startGame() {
        if (!_started) {
            _started = true;
            _stopwatchModel.restartReferenceTime();
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if (!_started) {
            return;
        }
        if (action == UserAction.LEFT) {
            _playerModel.getShip().rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _playerModel.getShip().rotate(+2);
        }
    }

    private void runGameLoop() {
        Timeline _timeline = new Timeline(new KeyFrame(Duration.millis(50), onFinished -> {
            update();
            render();
        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void update() {
        _playerModel.getShip().move();
        if(_passedBuoy.detectionPassageBuoy()) {
            _gameView.addBuoyPassedToDisplayedList(_playerModel.getLatestScore());
        }
    }

    private void render() {
        _gameView.update(_playerModel.getShip().getAngle(),_playerModel.getShip().getDx(),_playerModel.getShip().getDy(), _stopwatchModel.getStringFormatStopwatch(),_passedBuoy.getNextBuoyIndexInList());
    }
}
