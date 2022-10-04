package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;
    private Ellipse _boat;

    @FXML
    private AnchorPane _base;

    public void setGamePresenter( GamePresenter gamePresenter ) {
        _gamePresenter = gamePresenter;
    }

    public void rotate( Ellipse boat, double val ) {
        boat.setRotate(val);
    }

    public Ellipse drawBoat( double x, double y, double rx, double ry ) {
        Ellipse boat = new Ellipse(x, y, rx, ry);
        boat.setFill(Color.BLACK);
        _base.getChildren().add(boat);
        return boat;
    }

    public void move( Ellipse boat, double dx, double dy ) {
        boat.setLayoutX(boat.getLayoutX() + dx);
        boat.setLayoutY(boat.getLayoutY() + dy);
    }

    public void update( double dx, double dy, double angle ) {
        rotate(_boat, angle);
        move(_boat, dx, dy);
    }

    public void show() {
        _stage.show();
    }

    public void addBoat( double x, double y ) {
        _boat = drawBoat(x, y, 6, 16);
    }

    private void handleKeyPressed( KeyCode code ) {
        if (code == KeyCode.SPACE) {
            _gamePresenter.handleUserAction(UserAction.START);
        } else if (code == KeyCode.LEFT) {
            _gamePresenter.handleUserAction(UserAction.LEFT);
        } else if (code == KeyCode.RIGHT) {
            _gamePresenter.handleUserAction(UserAction.RIGHT);
        }
    }

    /* stage / scene
            superposer
                gridview
                    map
                anchorpane
                    boat


     */
    public static class GameViewFactory {

        private static int mapHeight = 500;
        private static int mapWidth = 500;

        @FXML
        private AnchorPane map;



        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }




        private static void fillMap(AnchorPane pane) {

            int nbCol = 20;
            int nbRow = 20;
            TileUI tile;

            for(int col=0 ; col<nbCol ; col++) {
                for(int row=0 ; row<nbRow ; row++) {
                    tile = new TileUI();
                    tile.setFitWidth((mapWidth/nbCol));
                    tile.setFitHeight((mapHeight/nbRow));
                    tile.setLayoutX((mapWidth/nbCol)*col);
                    tile.setLayoutY((mapHeight/nbRow)*row);
                    pane.getChildren().add(tile);
                }
            }
        }


        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            root.resize(mapWidth,mapHeight);

            fillMap((AnchorPane) root.lookup("#map"));

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