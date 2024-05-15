package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;
import cabbypatty.cab302_assignment.store.sqlite.SqliteConnection;
import cabbypatty.cab302_assignment.store.sqlite.UserDAO;
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
 * A class that controls Login page.
 */
public class LoginController {
    private Config config;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    /**
     * initializes a new instance of the LoginController class
     * @param config The config object to the config field of the LoginController instance
     */
    public LoginController(Config config) {
        System.out.println("LoginController created");
        this.config = config;
    }

    /**
     * A method that serves as an event handler for navigating to the registration page. It loads the registration page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the registration page to the user when triggered by an action event, clicking the registration event button.
     * @param event The event of clicking the registration event button.
     */
    @FXML
    private void navigateToRegistrationPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
            URL registrationView = getClass().getResource("/cabbypatty/cab302_assignment/views/registration.fxml");
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
     * A method that serves as an event handler for navigating to the main page of the application. It loads the main page layout from an FXML file, initializes its controller, creates a scene with the layout, sets it on the stage obtained from the event source, and displays the main page to the user when triggered by an action event, clicked the main button.
     * @param event The event of clicking main/home button
     */
    @FXML
    private void navigateToMainPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
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
     * A method that handles the login process by validating the user's credentials, creating a session upon successful authentication, and navigating to the main page of the application.
     * @param event The event of clicking login button.
     * @throws Exception
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
}
