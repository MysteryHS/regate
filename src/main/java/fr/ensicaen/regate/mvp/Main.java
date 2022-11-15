package fr.ensicaen.regate.mvp;

import fr.ensicaen.regate.mvp.presenter.LoginPresenter;
import fr.ensicaen.regate.mvp.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Timer;

public final class Main extends Application {
    private Timer _timer;

    public static void main( String[] args ) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("fr.ensicaen.regate.mvp.MessageBundle");
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        _timer = new Timer();
        primaryStage.setTitle(getMessageBundle().getString("project.title"));
        LoginView view = LoginView.LoginViewFactory.createView(primaryStage);
        LoginPresenter presenter = new LoginPresenter(_timer);
        presenter.setLoginView(view);
        view.setLoginPresenter(presenter);
        primaryStage.setResizable(false);
        view.show();
    }

    @Override
    public void stop() {
        System.out.println("Bye bye !");
        _timer.cancel();
    }
}