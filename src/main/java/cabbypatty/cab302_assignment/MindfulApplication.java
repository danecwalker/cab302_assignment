package cabbypatty.cab302_assignment;

import cabbypatty.cab302_assignment.controller.JournalEntryPageController;
import cabbypatty.cab302_assignment.controller.LoginController;
import cabbypatty.cab302_assignment.controller.MainPageController;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IJournalDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;
import cabbypatty.cab302_assignment.store.sqlite.AuthDAO;
//import cabbypatty.cab302_assignment.store.sqlite.JournalDAO;
import cabbypatty.cab302_assignment.store.sqlite.JournalDAO;
import cabbypatty.cab302_assignment.store.sqlite.SqliteConnection;
import cabbypatty.cab302_assignment.store.sqlite.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Main application class for the Mindful application.
 * Extends the JavaFX Application class to create a JavaFX application.
 */
public class MindfulApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Mindful";
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    public Config config;

    private final SqliteConnection sqliteConnection = new SqliteConnection();

    /**
     * The main entry point for all JavaFX applications.
     * This method is called when the application is launched.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If an error occurs during application startup.
     */
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
                    location = "/cabbypatty/cab302_assignment/views/main.fxml";
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
            System.out.println("Main view: " + type);
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

    /**
     * The main method.
     * This is the entry point for the Java application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}