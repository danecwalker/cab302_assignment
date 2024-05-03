package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.model.User;
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

public class LoginController implements Initializable {
    private IUserDAO userDAO;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void navigateToRegistrationPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
            URL registrationView = getClass().getResource("/cabbypatty/cab302_assignment/views/registration.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(registrationView);
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

    @FXML
    private void navigateToMainPage(ActionEvent event) {
        try {
            // Load the FXML file for the registration page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/main-page.fxml"));
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

    public void login(ActionEvent event) {
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

        User user = userDAO.getUser(email);
        if (user == null) {
            // User does not exist, show an error message
            showAlert("User Not Found", "No user with that email address exists.");
            return;
        }

        if (!user.hash_password.equals(password)) {
            // Password is incorrect, show an error message
            showAlert("Incorrect Password", "The password you entered is incorrect.");
            return;
        }

        navigateToMainPage(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userDAO = new UserDAO( new SqliteConnection() );
        System.out.println("LoginController initialized");
        System.out.println(userDAO);
    }
}
