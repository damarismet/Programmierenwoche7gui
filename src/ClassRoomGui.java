import gui.MainPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ClassRoomGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Create the components (well, just the main one, the rest is in there)
        Pane mainPane = new MainPane();

        // Kick-off and wait for events...
        StackPane rootPane = new StackPane(mainPane);

        Scene scene = new Scene(rootPane, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Classroom App");
        stage.show();
    }
}

