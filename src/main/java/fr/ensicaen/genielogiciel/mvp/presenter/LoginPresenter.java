package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.view.game.GameView;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;

import java.io.IOException;

public final class LoginPresenter {
    private ILoginView _loginView;

    public void setLoginView( LoginView loginView ) {
        _loginView = loginView;
    }

    public void launchGame( String nickName ) {
        if (nickName.isEmpty()) {
            _loginView.displayError(Main.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                GameView view = GameView.GameViewFactory.createView();
                Map map = new Map();
                BoatModel boat = new BoatModel();
                GamePresenter gamePresenter = new GamePresenter(nickName,map,boat);
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
