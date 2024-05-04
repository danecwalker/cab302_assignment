package cabbypatty.cab302_assignment.controller;

import static cabbypatty.cab302_assignment.utils.Alert.showAlert;
import static cabbypatty.cab302_assignment.utils.Email.*;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    String[] genders = {"Male", "Female", "Other"};

    @FXML // fx:id="genderCombo"
    private ComboBox<String> genderCombo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private DatePicker dobDatePicker;

    private Config config;

    public RegistrationController(Config config) {
        System.out.println("RegistrationController created");
        this.config = config;
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        genderCombo.setItems(FXCollections.observableArrayList(genders));
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        // Close the current registration window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Open the login-view page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/login.fxml"));

            loader.setControllerFactory((Class<?> type) -> {
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

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //DO USER REGISTRATION WITH DATABASE HERE -> LOGAN AND DANE
    @FXML
    private void onRegisterClick(ActionEvent event) throws Exception {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        LocalDate dob = dobDatePicker.getValue();

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

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            // Password or Confirm Password is empty, show an error message
            showAlert("Missing Password", "Please enter both password and confirm password.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            // Passwords don't match, show an error message
            showAlert("Password Mismatch", "Passwords do not match. Please try again.");
            return;
        }

        User user = config.getUserDAO().createUser(
                name,
                email,
                password,
                Date.valueOf(dob),
                genderCombo.getValue()
        );

        if (user == null) {
            // User already exists, show an error message
            showAlert("User Already Exists", "An account with this email already exists.");
            return;
        }


        // If everything is valid, continue with registration process
        // Close the current registration window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Open the main page or perform registration process
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/login.fxml"));

            loader.setControllerFactory((Class<?> type) -> {
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

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage mainStage = new Stage();
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
