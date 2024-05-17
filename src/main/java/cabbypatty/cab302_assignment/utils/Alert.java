package cabbypatty.cab302_assignment.utils;

/**
 * Utility class for displaying alert dialogs in a JavaFX application.
 */
public class Alert {

    /**
     * Displays an alert dialog with the specified title and message.
     *
     * @param title the title of the alert dialog
     * @param message the message to display in the alert dialog
     */
    public static void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
