package cabbypatty.cab302_assignment.controller;

//Imports
import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Journal;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import cabbypatty.cab302_assignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

/**
 * A class that controls entries for journal function page.
 */
public class JournalEntriesController implements Initializable {
    private Config config;
    private SessionAndUser sessionAndUser;

    @FXML
    private VBox journalEntries;

    /**
     * A method that sets up a controller for managing journal entries.
     * It initializes necessary configurations, validates a session, and ensures that the user is logged in before proceeding with any further actions.
     * If there are any errors during initialization or if the session is not valid, it throws a RuntimeException.
     * @param config The config that contains information related to authentication or session management.
     */
    public JournalEntriesController(Config config) {
        System.out.println("JournalEntriesController created");
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
     * A method that is responsible for retrieving the user associated with the current session.
     * Attempts to retrieve the user associated with the current session.
     * If the session is valid, it returns the associated user; otherwise, it redirects to the login page or returns null depending on the session validity. Any exceptions that occur during this process are wrapped and rethrown as RuntimeExceptions.
     * @return User object
     */
    private User getUser() {
        try {
            String sessionID = SessionStorage.loadToken();
            sessionAndUser = Session.validateSession(sessionID, config.getAuthDAO());
            if (sessionAndUser == null) {
                navigateToLogin();
            } else {
                return sessionAndUser.getUser();
            }
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * An event handler that loads a support page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the support page to the user when triggered by an action event, clicked the support button.
     * @param event The event clicking the support button
     */
    @FXML
    private void navigateToSupport(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/support.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == SupportController.class) {
                    return new SupportController(config);
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
     * An event handler that loads a setting page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the setting page to the user when triggered by an action event, clicked the setting button.
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

    /**
     * A method that add a new visual journal entry with a title, body, and mood image, and adds it to the UI.
     * @param journal The new journal entry
     */
    private void addJournalEntry(Journal journal) {
        Label journalEntryLabel = new Label(journal.getTitle());
        journalEntryLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        journalEntryLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        journalEntryLabel.setFont(new javafx.scene.text.Font("System Bold", 14));

        TextArea journalEntry = new TextArea();
        journalEntry.setWrapText(true);
        journalEntry.setText(journal.getBody());
        journalEntry.setLayoutY(28);

        VBox vBox = new VBox();
        vBox.getChildren().add(journalEntryLabel);
        vBox.getChildren().add(journalEntry);

        //Add journal entry to the scene in the scroll pane
        journalEntries.getChildren().add(vBox);
    }

    /**
     * A method that initializes and populates the UI with a userId and journal entry.
     * @param location The location of the url
     * @param resources The resources of resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Integer userId = getUser().id;
            System.out.println(userId);
            Journal[] journals = config.getJournalDAO().getJournals(userId);

            for (Journal journal : journals) {
                if (journal == null) {
                    continue;
                }
                addJournalEntry(journal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
