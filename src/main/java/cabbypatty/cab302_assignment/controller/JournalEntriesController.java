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
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

/**
 * Controller class for managing journal entries view.
 * Implements Initializable interface to initialize controller after its root element has been completely processed.
 */
public class JournalEntriesController implements Initializable {
    private Config config;
    private SessionAndUser sessionAndUser;

    @FXML
    private VBox journalEntries;
    @FXML
    private MenuButton username;

    /**
     * Constructor for JournalEntriesController.
     * Initializes the controller with the provided configuration and validates the user session.
     *
     * @param config The configuration object used for initializing the controller.
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
     * Retrieves the current user.
     *
     * @return The current user object.
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
     * Navigates to the support page.
     * Loads the FXML file for the support page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
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
     * Navigates to the calendar page.
     * Loads the FXML file for the calendar page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
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
     * Logs out the user.
     * Deletes the session from the authentication DAO and clears the session storage.
     * Navigates to the login page.
     *
     * @param event The action event triggered by the user.
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

            // Get the stage from the event source which is a menu item
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
     * Navigates to the journal page.
     * Loads the FXML file for the main page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToJournal(ActionEvent event) {
        try {
            // Load the FXML file for the main page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/journal.fxml"));

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
     * Adds a journal entry to the journal entries view.
     *
     * @param journal The journal entry to be added.
     */
    private void addJournalEntry(Journal journal) {
        VBox box = new VBox();
        box.setSpacing(8);

        HBox tcontainer = new HBox();
        tcontainer.setSpacing(8);
        Label title = new Label();
        title.setText(journal.getTitle());
        title.setStyle("-fx-text-fill: #949494;");
        tcontainer.getChildren().add(title);

        HBox tspacer = new HBox();
        tspacer.setMinWidth(0);
        HBox.setHgrow(tspacer, Priority.ALWAYS);
        tcontainer.getChildren().add(tspacer);

        Image deleteImage = new Image(getClass().getResourceAsStream("/cabbypatty/cab302_assignment/image/trash.png"));
        ImageView deleteImageView = new ImageView(deleteImage);
        deleteImageView.setFitHeight(16);
        deleteImageView.setFitWidth(16);
        deleteImageView.setPreserveRatio(true);
        deleteImageView.setSmooth(true);
        deleteImageView.onMouseClickedProperty().setValue(e -> {
            try {
                config.getJournalDAO().deleteJournal(journal.getId());
                journalEntries.getChildren().remove(box);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        tcontainer.getChildren().add(deleteImageView);

        HBox container = new HBox();
        container.setSpacing(8);
        container.setStyle("-fx-padding: 8; -fx-border-color: #d5d5d5; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-color: #ececec; -fx-max-width: 600;");
        HBox.setHgrow(container, Priority.ALWAYS);
        //Create label for journal entry
        Label journalEntryBody = new Label();
        journalEntryBody.setText(journal.getBody());
        journalEntryBody.wrapTextProperty().setValue(true);

        container.getChildren().add(journalEntryBody);

        HBox spacer = new HBox();
        spacer.setMinWidth(0);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        container.getChildren().add(spacer);

        Image moodImage = new Image(getClass().getResourceAsStream(journal.getMood().getImage()));
        ImageView moodImageView = new ImageView(moodImage);
        moodImageView.setFitHeight(20);
        moodImageView.setFitWidth(20);
        moodImageView.setPreserveRatio(true);
        moodImageView.setSmooth(true);

        container.getChildren().add(moodImageView);
        box.getChildren().add(tcontainer);
        box.getChildren().add(container);

        //Add journal entry to the scene in the scroll pane
        journalEntries.getChildren().add(box);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets the username label with the current user's name and loads journal entries.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(sessionAndUser.getUser().name);
        try {
            Integer userId = getUser().id;
            System.out.println(userId);
            Journal[] journals = config.getJournalDAO().getJournals(userId);
            System.out.println(journals.length);
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
