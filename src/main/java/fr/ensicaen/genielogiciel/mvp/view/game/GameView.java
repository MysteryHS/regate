package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;

    private ShipView _shipView;

    private MapView _mapView;

    private WindView _windView;

    @FXML
    private AnchorPane _base;

    @FXML
    private AnchorPane _mapPane;

    @FXML
    private AnchorPane _windWear;

    @FXML
    private Text _windText;

    public static int mapHeightInPixel = 500;
    public static int mapWidthInPixel = 500;


    public void setGamePresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }


    public void initView(MapView map, ShipView ship, WindView wind) {
        _mapView = map;
        _shipView = ship;
        _windView = wind;
    }



    @Override
    public void draw(double boatPosX, double boatPosY,String windDirection,double windKnot) {



        ImageView bg = new ImageView();
        bg.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/bg.png"));
        bg.setLayoutX(0);
        bg.setLayoutY(0);
        bg.setFitHeight(1080);
        bg.setFitWidth(1920);

        _mapView.draw(_mapPane);
        _shipView.draw(_mapPane,boatPosX,boatPosY);
        _windView.draw(_windText,windDirection,windKnot);
    }




    @Override
    public void update(Player playerModel) {

        _shipView.rotate(playerModel.getShip().getAngle());
        _shipView.move(playerModel.getShip().getDx(), playerModel.getShip().getDy());
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


    public static class GameViewFactory {



        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }





        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), Main.getMessageBundle());
            Parent root = loader.load();

            GameView view = loader.getController();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.resizableProperty().setValue(false);
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
