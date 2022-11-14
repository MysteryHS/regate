package fr.ensicaen.regate.model;

import fr.ensicaen.regate.mvp.view.LoginView;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoginPresenterTest {

    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
}