package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.MoodLevel;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

/**
 * Controller class for managing the journal entry page.
 * Implements Initializable interface to initialize controller after its root element has been completely processed.
 */
public class JournalEntryPageController implements Initializable {

    private Config config;
    private SessionAndUser sessionAndUser;

    @FXML
    private TextArea journalEntry;

    @FXML
    private Slider moodSlider;

    @FXML
    private ImageView moodImage;

    @FXML
    private MenuButton username;

    /**
     * Constructor for JournalEntryPageController.
     * Initializes the controller with the provided configuration and validates the user session.
     *
     * @param config The configuration object used for initializing the controller.
     */
    public JournalEntryPageController(Config config) {
        System.out.println("JournalEntryPageController created");
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
     * Navigates to the login page.
     * Loads the FXML file for the login page and sets the controller factory.
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
     * Navigates to the settings page.
     * Loads the FXML file for the settings page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
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
     * Navigates to the new journal entry page.
     * Loads the FXML file for the new journal entry page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToJournalNew(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/journal-new.fxml"));

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == JournalEntryPageController.class) {
                    return new JournalEntryPageController(config);
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
     * Logs out the user.
     * Deletes the session from the authentication DAO and clears the session storage.
     * Navigates to the login page.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void logout(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();

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

            // Set the new scene on the stage
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Navigates to the home page.
     * Loads the FXML file for the main page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToHome(ActionEvent event) {
        try {
            // Load the FXML file for the main page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/main.fxml"));

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
     * Navigates to the journal entries page.
     * Loads the FXML file for the journal entries page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToJournal(ActionEvent event) {
        try {
            // Load the FXML file for the main page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/journal.fxml"));

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
     * Submits the journal entry.
     * Creates a new journal entry with the text and mood level from the slider.
     * Navigates to the journal entries page.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void submitJournal(ActionEvent event) {
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

        String body = journalEntry.getText();

        if (body.isEmpty()) {
            return;
        }

        int mood = (int) moodSlider.getValue();

        config.getJournalDAO().createJournal(body, MoodLevel.fromLevel(mood), sessionAndUser.getUser().id);

        navigateToJournal(event);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets the username label with the current user's name and sets up the mood slider.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("JournalEntryPageController initialized");
        username.setText(sessionAndUser.getUser().name);
        moodSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return MoodLevel.fromLevel(object.intValue()).toString();
            }

            @Override
            public Double fromString(String string) {
                return switch (string) {
                    case "Very Bad" -> 1.0;
                    case "Bad" -> 2.0;
                    case "Good" -> 4.0;
                    case "Very Good" -> 5.0;
                    default -> 3.0;
                };
            }
        });

        moodSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Image image = new Image(getClass().getResourceAsStream(MoodLevel.fromLevel(Math.round(newValue.floatValue())).getImage()));
            moodImage.setImage(image);
        });
    }
}
