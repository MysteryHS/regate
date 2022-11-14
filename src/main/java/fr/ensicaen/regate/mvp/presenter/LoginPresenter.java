package fr.ensicaen.regate.mvp.presenter;

import fr.ensicaen.regate.mvp.Main;
import fr.ensicaen.regate.mvp.model.map.GameMap;
import fr.ensicaen.regate.mvp.model.ship.builder.builderType.TypeCrew;
import fr.ensicaen.regate.mvp.model.ship.builder.builderType.TypeSail;
import fr.ensicaen.regate.mvp.model.ship.builder.builderType.TypeShip;
import fr.ensicaen.regate.mvp.view.LoginView;
import fr.ensicaen.regate.mvp.view.game.GameView;

import java.io.IOException;
import java.util.Timer;

public final class LoginPresenter {
    private ILoginView _loginView;
    private final Timer _timer;

    public LoginPresenter(Timer timer){
        _timer = timer;
    }

    public void setLoginView( LoginView loginView ) {
        _loginView = loginView;
    }

    public void launchGame(String nickName, TypeShip typeShip, TypeSail typeSail , TypeCrew typeCrew) {
        if (nickName.isEmpty()) {
            _loginView.displayError(Main.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                GameView view = GameView.GameViewFactory.createView();
                GameMap map = new GameMap("carte1.txt");
                GamePresenter gamePresenter = new GamePresenter(map, typeShip, typeSail, typeCrew, _timer);
                view.setGamePresenter(gamePresenter);
                gamePresenter.setGameView(view);
                view.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            _loginView.close();
        }
    }
}
