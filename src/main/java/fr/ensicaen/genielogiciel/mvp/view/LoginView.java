package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.ILoginView;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeShip;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.builder.builderType.TypeSail;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginView<type> implements ILoginView {
    private LoginPresenter _loginPresenter;
    private Stage _stage;
    private Scene _scene;
    private ResourceBundle _ressource;
    private TypeSail _sail = TypeSail.NORMAL_SAIL;

    private TypeCrew _crew = TypeCrew.NORMAL_CREW;

    private TypeShip _boat = TypeShip.FIGARO37;


    @FXML
    private TextField _nickName;
    @FXML
    private Label _errorMessage;

    public void setLoginPresenter( LoginPresenter presenter ) {
        _loginPresenter = presenter;
    }
    public void setScene( Scene scene ) {
        _scene = scene;
    }

    public void setBundle( ResourceBundle bundle) {
        _ressource = bundle;
    }

    public void show() {
        _stage.show();
    }

    @Override
    public void close() {
        _stage.close();
    }

    @Override
    public void displayError( String message ) {
        _errorMessage.setText(message);
    }

    @FXML
    private void onClickOnStartGame() {
        _loginPresenter.launchGame(_nickName.getText(), _boat, _sail , _crew);
    }

    @FXML
    private void onClickChangeSail(){
        Label sail = (Label) _scene.lookup("#sail");
        if (_sail==TypeSail.NORMAL_SAIL){
            sail.setText(_ressource.getString("type.voile.big"));
            _sail=TypeSail.LARGE_SAIL;
        } else {
            sail.setText(_ressource.getString("type.voile.normal"));
            _sail=TypeSail.NORMAL_SAIL;
        }

    }

    @FXML
    private void onClickChangeCrew(){
        Label crew = (Label) _scene.lookup("#crew");
        if (_crew==TypeCrew.NORMAL_CREW){
            crew.setText(_ressource.getString("nbr.crewmates.4"));
            _crew=TypeCrew.MAX_CREW;
        } else {
            crew.setText(_ressource.getString("nbr.crewmates.2"));
            _crew=TypeCrew.NORMAL_CREW;
        }

    }
    @FXML
    private void onClickChangeBoat(){
        Label boattext = (Label) _scene.lookup("#boattext");
        if ( _boat== TypeShip.FIGARO37){
            boattext.setText("Oceanis");
            _boat= TypeShip.OCEANIS;
        } else {
            boattext.setText("Figaro");
            _boat= TypeShip.FIGARO37;
        }

    }

    public static class LoginViewFactory {
        private LoginViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static LoginView createView( Stage primaryStage ) throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("LoginDialog.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            LoginView view = loader.getController();
            Scene scene = new Scene(root);
            view._stage = primaryStage;

            primaryStage.setScene(scene);
            view.setScene(scene);
            view.setBundle(Main.getMessageBundle());

            return view;
        }
    }
}