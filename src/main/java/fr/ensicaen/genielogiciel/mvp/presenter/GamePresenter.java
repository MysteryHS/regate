package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;

public class GamePresenter {

    private final Model _model;
    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter( String nickName ) {
        Map map;
        try {
            map = new Map("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        _model = new Model(map);
        initGame(nickName);
    }

    private Player getFirstPlayer(){
        return _model.getFirstPlayer();
    }

    public void setGameView( IGameView gameView ) {
        _gameView = gameView;
        _gameView.addShip(getFirstPlayer().getShip().getX(), getFirstPlayer().getShip().getY());
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
            getFirstPlayer().getShip().rotate(-2);
        } else if (action == UserAction.RIGHT) {
            getFirstPlayer().getShip().rotate(+2);
        }
    }

    private void initGame(String nickname) {
        _model.createPlayer(nickname, new NormalSail(), new NormalCrew(), "polaire-figaro.pol");
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
        getFirstPlayer().getShip().move();
    }

    private void render() {
        _gameView.update(getFirstPlayer().getShip().getDx(), getFirstPlayer().getShip().getDy(), getFirstPlayer().getShip().getAngle());
    }
}
