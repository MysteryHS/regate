package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.map.GameMap;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.DataPolar;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.MaxCrewDecorator;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import fr.ensicaen.genielogiciel.mvp.view.game.GameView;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeBoat;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeSail;
import java.io.IOException;
import java.lang.reflect.Type;

public final class LoginPresenter {
    private ILoginView _loginView;

    public void setLoginView( LoginView loginView ) {
        _loginView = loginView;
    }

    public void launchGame(String nickName, TypeBoat typeBoat, TypeSail typeSail , TypeCrew typeCrew) {
        if (nickName.isEmpty()) {
            _loginView.displayError(Main.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                GameView view = GameView.GameViewFactory.createView();
                GameMap map = new GameMap("./src/main/resources/fr/ensicaen/genielogiciel/mvp/maps/carte1.txt");
                GamePresenter gamePresenter = new GamePresenter(nickName, map, typeBoat, typeSail, typeCrew);
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
