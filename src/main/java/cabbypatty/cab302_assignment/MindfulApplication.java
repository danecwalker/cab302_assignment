package cabbypatty.cab302_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MindfulApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Mindful";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    @Override
    public void start(Stage stage) throws IOException {
        URL main_view = MindfulApplication.class.getResource("views/login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(main_view);
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}