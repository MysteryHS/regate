package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.player.User;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.view.game.ShipView;
import fr.ensicaen.genielogiciel.mvp.view.game.MapView;
import fr.ensicaen.genielogiciel.mvp.view.game.TileView;
import fr.ensicaen.genielogiciel.mvp.view.game.WindView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class GamePresenter {
    private final Player _playerModel;

    private Map _mapModel;


    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter(String nickName, Map map, ShipModel ship) {
        _playerModel = new User(nickName,ship);

        _mapModel = map;
    }

    private void initView() {
        double caseWidthInPixel = MapView._mapWidthInPixel/ (double)_mapModel.getWidth();
        double caseHeightInPixel = MapView._mapHeightInPixel/ (double)_mapModel.getHeight();



        ShipView ship = new ShipView(caseWidthInPixel,caseHeightInPixel);
        WindView wind = new WindView();
        MapView map = new MapView(caseWidthInPixel,caseHeightInPixel,_mapModel.getWidth(), _mapModel.getHeight());
        for(int x=0; x< _mapModel.getWidth(); x++) {
            for(int y=0; y< _mapModel.getHeight(); y++) {
                map.addTile(new TileView(_mapModel.getTile(x,y),caseWidthInPixel,caseHeightInPixel,x,y));
            }
        }


        _gameView.initView(map,ship,wind);

        _gameView.draw( _playerModel.getShip().getX(),
                        _playerModel.getShip().getY(),
                        _mapModel.getWind().getWindDirection().name(),
                        _mapModel.getWind().getWindKnot());
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
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if (action == UserAction.LEFT) {
            _playerModel.getShip().rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _playerModel.getShip().rotate(+2);
        }
    }

    private void initGame() {
        DataPolar polar = null;
        try {
            polar = new DataPolar("polaire-figaro.pol");
        } catch (FileNotFoundException exception){
            System.err.println(exception.getMessage());
        }
    }

    private void runGameLoop() {
        _timeline = new Timeline(new KeyFrame(Duration.millis(50), onFinished -> {
            update();
            render();
        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void update() {
        _playerModel.getShip().move();
    }

    private void render() {
        _gameView.update(_playerModel);
    }
}
