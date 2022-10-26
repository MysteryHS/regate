package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.player.User;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.ship.command.MoveLeft;
import fr.ensicaen.genielogiciel.mvp.model.ship.command.MoveRight;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.LargeSailDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.Date;

public class GamePresenter {
    private final Player _playerModel;

    private Map _mapModel;


    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    private Date _dateStarted;

    public GamePresenter(String nickName, Map map, ShipModel ship) {
        _playerModel = new User(nickName,ship);
        _mapModel = map;
    }

    private void initView() {
        _gameView.draw(_mapModel,_playerModel);
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
            _dateStarted = new Date();
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if(!_started || _playerModel.getShip().isReplaying()){
            return;
        }
        if (action == UserAction.LEFT) {
            _playerModel.getShip().performCommand(new MoveLeft(_playerModel.getShip(), (new Date()).getTime() - _dateStarted.getTime()));
        } else if (action == UserAction.RIGHT) {
            _playerModel.getShip().performCommand(new MoveRight(_playerModel.getShip(), (new Date()).getTime() - _dateStarted.getTime()));
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
