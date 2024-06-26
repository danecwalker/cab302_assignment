package cabbypatty.cab302_assignment.controller;

//Imports
import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import static cabbypatty.cab302_assignment.utils.Alert.showAlert;
import static cabbypatty.cab302_assignment.utils.Email.isValidEmail;

/**
 * Controller class for managing the settings page.
 * Implements Initializable interface to initialize controller after its root element has been completely processed.
 */
public class SettingsController implements Initializable {
    String[] genders = {"Male", "Female", "Other"};
    private Config config;
    private SessionAndUser sessionAndUser;
    @FXML
    private ComboBox<String> genderCombo;
    @FXML
    private MenuButton username;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private Button saveDetails;

    /**
     * Constructor for SettingsController.
     * Initializes the controller with the provided configuration and validates the user session.
     *
     * @param config The configuration object used for initializing the controller.
     */
    public SettingsController(Config config) {
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
     * Navigates to the journal entries page.
     * Loads the FXML file for the journal entries page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToJournalPage(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
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
     * Initializes the controller after its root element has been completely processed.
     * Sets the username label with the current user's name.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderCombo.setItems(FXCollections.observableArrayList(genders));
        username.setText(sessionAndUser.getUser().name);
        genderCombo.setValue(sessionAndUser.getUser().gender);
        nameField.setText(sessionAndUser.getUser().name);
        emailField.setText(sessionAndUser.getUser().email);
        dobDatePicker.setValue(sessionAndUser.getUser().dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @FXML
    private void onSaveDetailsClick(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate dob = dobDatePicker.getValue();
        String gender = genderCombo.getValue();

        if (name.isEmpty()) {
            // Name field is empty, show an error message
            showAlert("Invalid Name", "Please enter your name.");
            return;
        }

        if (dob == null) {
            // Date of Birth is not selected, show an error message
            showAlert("Invalid Date of Birth", "Please select your date of birth.");
            return;
        }

        // Check if the user is at least 13 years old
        LocalDate today = LocalDate.now();
        LocalDate minimumDateOfBirth = today.minusYears(13);
        if (dob.isAfter(minimumDateOfBirth)) {
            showAlert("Underage User", "You must be at least 13 years old to use the app.");
            return;
        }

        if (!isValidEmail(email)) {
            // Email is not well-formed, show an error message
            showAlert("Invalid Email", "Please enter a valid email address.");
            return;
        }

        try {
            config.getUserDAO().updateUser(sessionAndUser.getUser().id , name , email , Date.valueOf(dob) , gender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
