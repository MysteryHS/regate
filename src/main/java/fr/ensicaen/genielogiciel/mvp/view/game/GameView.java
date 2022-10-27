package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.player.Player;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

    private ChronoView _chronoView;

    private ChronoList _chronoList;

    @FXML
    private AnchorPane _base;

    @FXML
    private AnchorPane _mapPane;

    @FXML
    private AnchorPane _windWear; // FIXME jamais utilisé

    @FXML
    private Text _windText;

    @FXML
    private AnchorPane _chronoPane;

    public static int mapHeightInPixel = 500;
    public static int mapWidthInPixel = 500;


    public void setGamePresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }


    public void initView(MapView map, ShipView ship, WindView wind) {
        _mapView = map;
        _shipView = ship;
        _windView = wind;
        _chronoView = new ChronoView();
        _chronoList = new ChronoList(_chronoPane);
    }



    @Override
    public void draw(double boatPosX, double boatPosY,String windDirection,double windKnot) {
        _mapView.draw(_mapPane);
        _shipView.draw(_mapPane,boatPosX,boatPosY);
        _windView.draw(_windText,windDirection,windKnot);
        _chronoView.draw(_chronoPane);

    }

    @Override
    public void addBuoyPassedToDisplayedList(String chrono) {
        _chronoList.addChrono(new ChronoItem(chrono));
    }

    public void isNextBuoy(int index) {
        _mapView.isNextBuoy(index);
    }

    @Override
    public void update(double angle, double dx,double dy,String chrono,int indexInListNextBuoy) {
        _shipView.rotate(angle);
        _shipView.move(dx, dy);
        isNextBuoy(indexInListNextBuoy);
        _chronoView.refresh(chrono);
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
        } else if (code == KeyCode.R) {
            _gamePresenter.handleUserAction(UserAction.RESET);
        }
    }


    public static class GameViewFactory {



        private GameViewFactory() {
            // FIXME supprimer ce commentaire qui n'était qu'à vertue pédagogique
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
