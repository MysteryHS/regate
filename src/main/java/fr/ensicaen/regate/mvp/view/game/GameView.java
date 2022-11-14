package fr.ensicaen.regate.mvp.view.game;

import fr.ensicaen.regate.mvp.Main;
import fr.ensicaen.regate.mvp.presenter.GamePresenter;
import fr.ensicaen.regate.mvp.presenter.IGameView;
import fr.ensicaen.regate.mvp.presenter.UserAction;
import fr.ensicaen.regate.mvp.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private StopwatchView _stopwatchView;
    private StopwatchList _stopwatchList;
    @FXML
    private AnchorPane _mapPane;
    @FXML
    private Text _windText;
    @FXML
    private AnchorPane _stopwatchPane;

    public static int mapHeightInPixel = 800;
    public static int mapWidthInPixel = 600;
    public void setGamePresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }
    @Override
    public void initView(MapView map, ShipView ship, WindView wind) {
        _mapView = map;
        _shipView = ship;
        _windView = wind;
        _stopwatchView = new StopwatchView();
        _stopwatchList = new StopwatchList(_stopwatchPane);
    }
    @Override
    public void draw(double boatPosX, double boatPosY,String windDirection,double windKnot) {
        _mapView.draw(_mapPane);
        _shipView.draw(_mapPane,boatPosX,boatPosY);
        _windView.draw(_windText,windDirection,windKnot);
        _stopwatchView.draw(_stopwatchPane);
    }
    @Override
    public void addBuoyPassedToDisplayedList(String stopwatch) {
        _stopwatchList.addStopwatchItem(new StopwatchItem(stopwatch));
    }

    public void isNextBuoy(int index) {
        _mapView.isNextBuoy(index);
    }

    @Override
    public void update(double angle, double x, double y, String stopwatch, int indexInListNextBuoy) {
        _shipView.rotate(angle);
        _shipView.move(x, y);
        isNextBuoy(indexInListNextBuoy);
        _stopwatchView.refresh(stopwatch);
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

        private GameViewFactory() {}

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
