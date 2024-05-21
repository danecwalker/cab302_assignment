package cabbypatty.cab302_assignment;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
public class MindfulNotification {
    // @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        Alert alert = new Alert(AlertType.NONE, "", ButtonType.CLOSE);

        alert.setTitle("Today's Mindful Notification");
        alert.getDialogPane().setHeaderText("Did you know...");
        alert.getDialogPane().setContentText("Drinking 2L water is good for your both physical and mental health!! Did you already drink 2L today?");

        ButtonType button  = alert.showAndWait().orElse(ButtonType.CANCEL );
        System.out.println( button.toString() );
    }

}
