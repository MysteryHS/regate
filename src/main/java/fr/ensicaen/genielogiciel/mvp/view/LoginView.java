package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.ILoginView;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeBoat;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeCrew;
import fr.ensicaen.genielogiciel.mvp.view.game.type.TypeSail;
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
    private TypeSail _sail = TypeSail.NORM;

    private TypeCrew _crew = TypeCrew.TWO;

    private TypeBoat _boat = TypeBoat.FIGARO;


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
        _loginPresenter.launchGame(_nickName.getText());
    }

    @FXML
    private void onClickChangeSail(){
        Label sail = (Label) _scene.lookup("#sail");
        if (_sail==TypeSail.NORM){
            sail.setText(_ressource.getString("type.voile.big"));
            _sail=TypeSail.BIG;
        } else {
            sail.setText(_ressource.getString("type.voile.normal"));
            _sail=TypeSail.NORM;
        }

    }

    @FXML
    private void onClickChangeCrew(){
        Label crew = (Label) _scene.lookup("#crew");
        if (_crew==TypeCrew.TWO){
            crew.setText(_ressource.getString("nbr.crewmates.4"));
            _crew=TypeCrew.FOUR;
        } else {
            crew.setText(_ressource.getString("nbr.crewmates.2"));
            _crew=TypeCrew.TWO;
        }

    }
    @FXML
    private void onClickChangeBoat(){
        Label boattext = (Label) _scene.lookup("#boattext");
        if ( _boat==TypeBoat.FIGARO){
            boattext.setText("Oceanis");
            _boat=TypeBoat.OCEANIS;
        } else {
            boattext.setText("Figaro");
            _boat=TypeBoat.FIGARO;
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