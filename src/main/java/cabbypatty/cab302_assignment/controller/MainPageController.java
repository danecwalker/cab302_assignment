package cabbypatty.cab302_assignment.controller;

import cabbypatty.cab302_assignment.Config;
import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.model.Journal;
import cabbypatty.cab302_assignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller class for managing the main page.
 * Implements Initializable interface to initialize controller after its root element has been completely processed.
 */
public class MainPageController implements Initializable {
    @FXML
    private Text welcomeText;

    @FXML
    private Text currentDate;

    @FXML
    private MenuButton username;

    @FXML
    private LineChart<String, String> weekly_mood;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private CategoryAxis yAxis;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets the welcome text with the current user's first name.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainPageController initialized");
        try {
            String sessionId = SessionStorage.loadToken();
            User user = config.getAuthDAO().getSessionAndUser(sessionId).getUser();
            String firstName = user.name.split(" ")[0];
            welcomeText.setText("How Do You Feel Today, " + firstName + "?");
            username.setText(user.name);
            LocalDate date = LocalDate.now();
            currentDate.setText(date.format(java.time.format.DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            weekly_mood.setTitle("Weekly Mood");
            weekly_mood.setAnimated(true);
            xAxis.setLabel("Day");
            yAxis.setLabel("Mood Level");

            // Create a series for the mood levels
            weekly_mood.getData().clear();
            Journal[] journals = config.getJournalDAO().getLast7DaysJournals(user.id);
            XYChart.Series<String, String> series = new XYChart.Series<>();
            ZoneId systemTimeZone = ZoneId.systemDefault();

            for (int i = journals.length-1; i > -1; i--) {
                Journal journal = journals[i];
                System.out.println(journal.getTitle());
                ZonedDateTime localDateTime = journal.getDate().toInstant().atZone(systemTimeZone);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
                series.getData().add(new XYChart.Data<>(localDateTime.format(formatter), journal.getMood().toString()));
            }

            weekly_mood.getData().add(series);
            weekly_mood.legendVisibleProperty().setValue(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Config config;

    /**
     * Constructor for MainPageController.
     * Initializes the controller with the provided configuration.
     *
     * @param config The configuration object used for initializing the controller.
     */
    public MainPageController(Config config) {
        System.out.println("MainPageController created");
        this.config = config;
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

}
