package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.map.Map;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.cert.PolicyNode;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;

    private BoatView _boat;

    private MapView _map;

    @FXML
    private AnchorPane _base;

    @FXML
    private AnchorPane _mapPane;

    public void setGamePresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }






    @Override
    public void draw(Map mapModel, BoatModel boatModel) {
        _map = new MapView(this,mapModel);
        _boat = new BoatView(this,boatModel);
        if(_mapPane==null) {
            System.out.println("mapPane est null");
        }
        _map.draw(_mapPane);
        _boat.draw(_mapPane);
    }






    @Override
    public void update(BoatModel boatModel, PlayerModel playerModel) {

        _boat.rotate(boatModel.getAngle());
        _boat.move(boatModel.getDx(), boatModel.getDy());
    }

    public void show() {
        _stage.show();
    }





    private void handleKeyPressed(KeyCode code) {
        if (code == KeyCode.SPACE) {
            _gamePresenter.handleUserAction(UserAction.START);
        } else if (code == KeyCode.LEFT) {
            _gamePresenter.handleUserAction(UserAction.LEFT);
        } else if (code == KeyCode.RIGHT) {
            _gamePresenter.handleUserAction(UserAction.RIGHT);
        }
    }

    /*
    public AnchorPane getMapPane() {
        return _mapPane;
    }*/


    public static class GameViewFactory {



        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }





        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            root.resize(MapView.mapWidth, MapView.mapHeight);


            GameView view = loader.getController();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.setTitle(Main.getMessageBundle().getString("project.title"));
            stage.setScene(scene);
            _stage = stage;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                KeyCode code = event.getCode();
                view.handleKeyPressed(code);
            });


            return view;
        }
    }
}
