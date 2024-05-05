package cabbypatty.cab302_assignment;

import cabbypatty.cab302_assignment.controller.LoginController;
import cabbypatty.cab302_assignment.controller.MainPageController;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IJournalDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;
import cabbypatty.cab302_assignment.store.sqlite.AuthDAO;
import cabbypatty.cab302_assignment.store.sqlite.JournalDAO;
import cabbypatty.cab302_assignment.store.sqlite.SqliteConnection;
import cabbypatty.cab302_assignment.store.sqlite.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;

public class MindfulApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Mindful";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    public Config config;

    private final SqliteConnection sqliteConnection = new SqliteConnection();

    @Override
    public void start(Stage stage) throws Exception {
        sqliteConnection.setupTables();

        try {
            IAuthDAO authDAO = new AuthDAO(sqliteConnection);
            IUserDAO userDao = new UserDAO(sqliteConnection);
            IJournalDAO journalDAO = new JournalDAO(sqliteConnection);

            config = new Config(userDao, authDAO, journalDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String location = "/cabbypatty/cab302_assignment/views/login.fxml";

        String sessionToken = SessionStorage.loadToken();
        if (sessionToken != null) {
            try {
                SessionAndUser session_user = config.getAuthDAO().getSessionAndUser(sessionToken);

                if (session_user != null) {
                    location = "/cabbypatty/cab302_assignment/views/main-page.fxml";
                } else {
                    SessionStorage.clearToken();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        URL main_view = MindfulApplication.class.getResource(location);
        FXMLLoader fxmlLoader = new FXMLLoader(main_view);

        fxmlLoader.setControllerFactory((Class<?> type) -> {
            if (type == LoginController.class) {
                return new LoginController(config);
            } else if (type == MainPageController.class) {
                return new MainPageController(config);
            } else {
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        });

        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        stage.onCloseRequestProperty().setValue(e -> {
            sqliteConnection.close();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}