package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainPageController initialized");
        try {
            String sessionId = SessionStorage.loadToken();
            User user = config.getAuthDAO().getSessionAndUser(sessionId).getUser();
            String firstName = user.name.split(" ")[0];
            welcomeText.setText("How Do You Feel Today, " + firstName + "?");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Config config;

    public MainPageController(Config config) {
        System.out.println("MainPageController created");
        this.config = config;
    }

    @FXML
    private void navigateToJournalPage(ActionEvent event) {
        try {
            // Load the FXML file for the journal entry page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cabbypatty/cab302_assignment/views/journal-entry-page.fxml"));
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

}
