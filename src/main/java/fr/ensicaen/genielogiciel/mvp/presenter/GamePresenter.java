package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.sail.LargeSailDecorator;
import fr.ensicaen.genielogiciel.mvp.model.sail.NormalSail;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class GamePresenter {
    private final PlayerModel _playerModel;
    private BoatModel _boatModel;
    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter( String nickName ) {
        _playerModel = new PlayerModel();
        _playerModel.setNickname(nickName);
        initGame();
    }

    public void setGameView( IGameView gameView ) {
        _gameView = gameView;
        _gameView.addBoat(_boatModel.getX(), _boatModel.getY());
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
            _boatModel.rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _boatModel.rotate(+2);
        }
    }

    private void initGame() {
        DataPolar polar = null;
        try {
            polar = new DataPolar("polaire-figaro.pol");
        } catch (FileNotFoundException exception){
            System.err.println(exception.getMessage());
        }
        _boatModel = new BoatModel(new LargeSailDecorator(new NormalSail()), new MaxCrewDecorator(new NormalCrew()), new WindProxy(0,0), polar);
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
        _boatModel.move();
    }

    private void render() {
        _gameView.update(_boatModel.getDx(), _boatModel.getDy(), _boatModel.getAngle());
    }
}
