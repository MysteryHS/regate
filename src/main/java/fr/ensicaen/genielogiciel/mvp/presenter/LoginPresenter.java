package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.PassedBuoy;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
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
                Map map = new Map("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
                ShipModel boat = new ShipModel(
                        new NormalSail(),
                        new MaxCrewDecorator(new NormalCrew()),
                        new WindProxy(50,50),
                        new DataPolar("polaire-figaro.pol"));
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
