package fr.ensicaen.genielogiciel.mvp.presenter;


import fr.ensicaen.genielogiciel.mvp.model.Chrono;
import fr.ensicaen.genielogiciel.mvp.model.Collision;
import fr.ensicaen.genielogiciel.mvp.model.Collision;
import fr.ensicaen.genielogiciel.mvp.model.PassedBuoy;
import fr.ensicaen.genielogiciel.mvp.model.Stopwatch;

import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStationProxy;

import fr.ensicaen.genielogiciel.mvp.model.map.Buoy;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.map.Tile;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.player.User;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.ship.command.MoveLeft;
import fr.ensicaen.genielogiciel.mvp.model.ship.command.MoveRight;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ConcreteShipBuilder;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ShipDirector;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeSail;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeShip;
import fr.ensicaen.genielogiciel.mvp.view.game.ShipView;
import fr.ensicaen.genielogiciel.mvp.view.game.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.util.Date;

public class GamePresenter {
    private final Player _playerModel;
    private final Stopwatch _stopwatchModel;
    private final PassedBuoy _passedBuoy;
    private final Collision _collision;
    private final GameMap _mapModel;
    private IGameView _gameView;
    private boolean _started = false;
    private Date _dateStarted;


    public GamePresenter(GameMap map, TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipModel ship;
        _stopwatchModel = Stopwatch.getInstance();
        _mapModel = map;
        ship = initGame(typeShip, typeSail, typeCrew);
        _playerModel = new User(ship);
        _passedBuoy = new PassedBuoy(_playerModel,_mapModel);
        _collision = new Collision(map, ship);
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

        //TODO correct this but i don't see another way to do this :(
        _playerModel.getShip().setWidth(1);
        _playerModel.getShip().setHeight(1.2);

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

    public void resetShip(long delayEnd){
        _playerModel.getShip().replay(delayEnd);
    }

    public void handleUserAction( UserAction code ) {
        if (code == UserAction.START) {
            startGame();
        } else if(code == UserAction.RESET) {
            resetShip((new Date()).getTime() - _dateStarted.getTime());
        } else {
            changeDirection(code);
        }
    }

    private void startGame() {
        if (!_started) {
            _started = true;
            _stopwatchModel.restartReferenceTime();
            _dateStarted = new Date();
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if(!_started || !_playerModel.getShip().canRotate()){
            return;
        }
        if (action == UserAction.LEFT) {
            _playerModel.getShip().performCommand(new MoveLeft(_playerModel.getShip(), (new Date()).getTime() - _dateStarted.getTime()));
        } else if (action == UserAction.RIGHT) {
            _playerModel.getShip().performCommand(new MoveRight(_playerModel.getShip(), (new Date()).getTime() - _dateStarted.getTime()));
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
        _collision.setMoveShip();
        _playerModel.getShip().move();
        if(_passedBuoy.detectionPassageBuoy()) {
            _gameView.addBuoyPassedToDisplayedList(_playerModel.getLatestScore());
        }
    }

    private void render() {
        _gameView.update(_playerModel.getShip().getAngle(),_playerModel.getShip().getX(),_playerModel.getShip().getY(), _stopwatchModel.getStringFormatStopwatch(),_passedBuoy.getNextBuoyIndexInList());
    }
}
