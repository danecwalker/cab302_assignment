package cabbypatty.cab302_assignment.controller;

//Imports
import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.prefs.BackingStoreException;

/**
 * A class that controls Support page.
 */
public class SupportController {

    private Config config;

    private SessionAndUser sessionAndUser;

    /**
     * A constructor that initializes the SupportController instance by setting up the configuration and validating the user session. If the user is not authenticated, it redirects them to the login page.
     * @param config  the SupportController instance
     */
    public SupportController(Config config) {
        System.out.println("SupportController created");
        this.config = config;
        try {
            String sessionID = SessionStorage.loadToken();
            this.sessionAndUser = Session.validateSession(sessionID, config.getAuthDAO());
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (sessionAndUser == null) {
            navigateToLogin();
        }
    }

    /**
     * A method that loads the login page layout from an FXML file, initializes its controller, creates a scene with the layout, opens a new stage, sets the scene on the stage, and displays the login page to the user.
     */
    private void navigateToLogin() {
        try {
            // Load the FXML file for the login page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/login.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == LoginController.class) {
                    return new LoginController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = new Stage();

            // Set the new scene on the stage
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * An event handler that loads a setting page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the support page to the user when triggered by an action event, clicked the setting button.
     * @param event The event clicking the setting button
     */
    @FXML
    private void navigateToSettings(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/settings.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == SettingsController.class) {
                    return new SettingsController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene journalEntryScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(journalEntryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * A method that facilitates the navigation to a journal entry page in a JavaFX application when an action event occurs, such as a button click. It does this by loading the appropriate FXML file, setting up the controller, creating a new scene with the loaded FXML file, and then replacing the current scene on the stage with the newly created scene.
     * @param event The event of clicked the journal button
     */
    @FXML
    private void navigateToJournalPage(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/journal-entries.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == JournalEntriesController.class) {
                    return new JournalEntriesController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene journalEntryScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(journalEntryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Acting as an event handler for navigating to a calendar view. It loads the calendar view layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the calendar view to the user when triggered by an action event, clicked a calendar button.
     * @param event The event clicking a celendar button
     */
    @FXML
    private void navigateToCalendar(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/calendar.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == CalendarController.class) {
                    return new CalendarController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene journalEntryScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(journalEntryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    /**
     * A method that logs out the user by deleting the session, clears the session token, loads the login page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the login page to the user when triggered by an action event which is clicked the logout button.
     * @param event the event clicking logout button
     */
    @FXML
    private void logout(ActionEvent event) {
        try {
            String sessionId = SessionStorage.loadToken();
            config.getAuthDAO().deleteSession(sessionId);
            SessionStorage.clearToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // Load the FXML file for the login page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/login.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == LoginController.class) {
                    return new LoginController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Handling an action event, clicking a Home button. It shows the stage from the event source, page of home/main.
     * If exception occurs, this prints the stack trace for the diagnosing.
     * @param event The event a user clicks home button
     */
    @FXML
    private void home(ActionEvent event) {
        try {
            // Load the FXML file for the main page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/main-page.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == MainPageController.class) {
                    return new MainPageController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            Scene mainPageScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(mainPageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
