package fr.ensicaen.genielogiciel.model;

import fr.ensicaen.genielogiciel.mvp.view.LoginView;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoginPresenterTest {

    @Mock
    private LoginView _view;
    private AutoCloseable closeable;

    LoginPresenterTest(LoginView view) {
        _view = view;
    }

    @BeforeEach
    public void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    //TODO shity test
    /*
    @Test
    void should_activate_view_error_display() {
        // TODO this interest of this test holds on the use of Mockito

        // given
        LoginPresenter presenter = new LoginPresenter();
        presenter.setLoginView(_view);

        // when
        //presenter.launchGame("");

        // then
        verify(_view, times(1)).displayError(Main.getMessageBundle().getString("error.nickname"));
    }
     */
}