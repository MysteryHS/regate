package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.PassedBuoy;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStationProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.player.User;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ConcreteShipBuilder;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.ShipDirector;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeShip;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeSail;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.FileNotFoundException;

public class GamePresenter {
    private final Player _playerModel;
    private final GameMap _mapModel;

    private PassedBuoy _passedBuoy;
    private IGameView _gameView;
    private boolean _started = false;

    @Deprecated
    public GamePresenter(String nickName, GameMap map, ShipModel ship) {
        _playerModel = new User(nickName,ship);
        _mapModel = map;
    }

    public GamePresenter(String nickName, GameMap map, TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipModel ship;
        ship = initGame(typeShip, typeSail, typeCrew);
        _playerModel = new User(nickName,ship);
        _mapModel = map;
        _passedBuoy = new PassedBuoy(_playerModel, _mapModel);
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
        if (!_started) {
            return;
        }
        if (action == UserAction.LEFT) {
            _playerModel.getShip().rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _playerModel.getShip().rotate(+2);
        }
    }

    private ShipModel initGame(TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) throws FileNotFoundException {
        ShipDirector director = new ShipDirector(new ConcreteShipBuilder());
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
        director.addWind(new WeatherStationProxy(0.3,49));
        return director.build();

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
        _passedBuoy.detectionPassageBuoy();
    }

    private void render() {
        _gameView.update(_playerModel);
    }
}
