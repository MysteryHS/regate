package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.BoatModel;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GamePresenter {
    private final PlayerModel _playerModel;
    private BoatModel _boatModel;
    private Map _mapModel;


    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter(String nickName, Map map, BoatModel boat) {
        _playerModel = new PlayerModel();
        _playerModel.setNickname(nickName);

        _mapModel = map;
        _boatModel = boat;
    }

    private void initView() {
        _gameView.draw(_mapModel,_boatModel);
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
            _boatModel.rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _boatModel.rotate(+2);
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
        _boatModel.move();
    }

    private void render() {
        _gameView.update(_boatModel.getDx(), _boatModel.getDy(), _boatModel.getAngle());
    }
}
