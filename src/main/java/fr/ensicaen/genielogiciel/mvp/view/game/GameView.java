package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import fr.ensicaen.genielogiciel.mvp.view.TileUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;

    private BoatUI _boat;

    @FXML
    private AnchorPane _base;

    @FXML
    private AnchorPane _map;
    private static int mapHeight = 500;
    private static int mapWidth = 500;

    public void setGamePresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }




    public void rotate(BoatUI boat, double val) {
        boat.setRotate(val);
    }

    public void drawMap() {
        int nbCol = 20;
        int nbRow = 20;
        double sizeWidth = (mapWidth / nbCol);
        double sizeHeight = (mapHeight / nbRow);

        for (int col = 0; col < nbCol; col++) {
            for (int row = 0; row < nbRow; row++) {
                TileUI tile = new TileUI();
                tile.setFitWidth(sizeWidth);
                tile.setFitHeight(sizeHeight);
                tile.setLayoutX(sizeWidth * col);
                tile.setLayoutY(sizeHeight * row);
                _map.getChildren().add(tile);
            }
        }
    }

    public BoatUI drawBoat(double x, double y, double rx, double ry) {

        BoatUI boat = new BoatUI(this,x,y,rx,ry);
        _map.getChildren().add(boat);
        return boat;
    }

    public void move(BoatUI boat, double dx, double dy) {
        boat.move(dx,dy);
    }

    public void update(double dx, double dy, double angle) {
        rotate(_boat, angle);
        move(_boat, dx, dy);
    }

    public void show() {
        _stage.show();
    }

    public void addBoat(double x, double y) {
        _boat = drawBoat(x, y, 34, 44);
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


    public static class GameViewFactory {



        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }





        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            root.resize(mapWidth, mapHeight);


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
