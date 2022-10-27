package fr.ensicaen.genielogiciel.mvp;

import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public final class Main extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle");
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        primaryStage.setTitle(getMessageBundle().getString("project.title"));
        LoginView view = LoginView.LoginViewFactory.createView(primaryStage);
        LoginPresenter presenter = new LoginPresenter();
        presenter.setLoginView(view);
        view.setLoginPresenter(presenter);
        primaryStage.setResizable(false);
        view.show();
    }

    @Override
    public void stop() {
        getMessageBundle().getString("project.bye");
    }
}