package fr.ensicaen.genielogiciel.mvp.presenter;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.player.User;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
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

public class GamePresenter {
    private final Player _playerModel;
    private final GameMap _mapModel;
    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter(String nickName, GameMap map, ShipModel ship) {
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
