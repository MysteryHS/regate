package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;

// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.player.User;

import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ConcreteShipBuilder;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ShipDirector;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.LargeSailDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeBoat;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeSail;
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

    @Deprecated
    public GamePresenter(String nickName, Map map, ShipModel ship) {
        _playerModel = new User(nickName,ship);

        _mapModel = map;
    }

    public GamePresenter(String nickName, Map map, TypeBoat typeBoat, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipModel ship;
        ship = initGame(typeBoat, typeSail, typeCrew);
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

    private ShipModel initGame(TypeBoat typeBoat, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipDirector director = new ShipDirector(new ConcreteShipBuilder());
        if (typeBoat == TypeBoat.FIGARO) {
            director.buildFigaro();
        } else {
            director.buildOceanis37();
        }
        if (typeSail == TypeSail.NORM) {
            director.buildNormalSail();
        } else {
            director.buildLargerSail();
        }
        if (typeCrew == TypeCrew.TWO) {
            director.buildNormalCrew();
        } else {
            director.buildMaxCrew();
        }
        director.addWind(new WindProxy(0.3,49));
        ShipModel ship = director.build();
        return ship;

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
