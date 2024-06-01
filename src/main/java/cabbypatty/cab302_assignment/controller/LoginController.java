package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static cabbypatty.cab302_assignment.utils.Alert.showAlert;
import static cabbypatty.cab302_assignment.utils.Email.isValidEmail;

/**
 * Controller class for managing the login page.
 * Implements Initializable interface to initialize controller after its root element has been completely processed.
 */
public class LoginController implements Initializable {
    private Config config;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    /**
     * Constructor for LoginController.
     * Initializes the controller with the provided configuration.
     *
     * @param config The configuration object used for initializing the controller.
     */
    public LoginController(Config config) {
        System.out.println("LoginController created");
        this.config = config;
    }

    /**
     * Navigates to the registration page.
     * Loads the FXML file for the registration page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToRegistrationPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
            URL registrationView = getClass().getResource("/cabbypatty/cab302_assignment/views/register.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(registrationView);

            fxmlLoader.setControllerFactory((Class<?> type) -> {
                if (type == RegistrationController.class) {
                    return new RegistrationController(config);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            Scene registrationScene = new Scene(fxmlLoader.load());
            stage.setScene(registrationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Navigates to the main page.
     * Loads the FXML file for the main page and sets the controller factory.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToMainPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
            URL registrationView = getClass().getResource("/cabbypatty/cab302_assignment/views/main.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(registrationView);

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

            Scene registrationScene = new Scene(fxmlLoader.load());

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(registrationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Handles the login action.
     * Validates the email and password, creates a new session if the credentials are correct, and navigates to the main page.
     *
     * @param event The action event triggered by the user.
     * @throws Exception If an error occurs during login.
     */
    public void login(ActionEvent event) throws Exception {
        // Get the email and password from the fields
        String email = emailField.getText();
        String password = passwordField.getText();
        if (!isValidEmail(email)) {
            // Email is not well-formed, show an error message
            showAlert("Invalid Email", "Please enter a valid email address.");
            return;
        }

        if (password.isEmpty()) {
            // Password or Confirm Password is empty, show an error message
            showAlert("Missing Password", "Please enter your password.");
            return;
        }

        User user = config.getUserDAO().getUser(email);
        if (user == null) {
            // User does not exist, show an error message
            showAlert("User Not Found", "No user with that email address exists.");
            return;
        }

        if (!user.hash_password.equals(password)) {
            // Password is incorrect, show an error message
            showAlert("Incorrect Password", "Incorrect email or password. Please try again.");
            return;
        }

        Session session = new Session(user.id);
        config.getAuthDAO().setSession(session);
        SessionStorage.saveToken(session.sessionID);


        navigateToMainPage(event);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LoginController initialized");
    }
}
