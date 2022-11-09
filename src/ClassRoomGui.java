import gui.MainPane;
import io.MajorMapReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Map;

public class ClassRoomGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // First, we read in the map that translates the codes for a major to a longer description
        MajorMapReader mapReader = new MajorMapReader("src/io/major-map.txt");
        Map<String, String> majorMap = mapReader.readMajorMap();

        // Create the components (well, just the main one, the rest is in there)
        Pane mainPane = new MainPane(majorMap);
        // Kick-off and wait for events...
        StackPane rootPane = new StackPane(mainPane);
        Scene scene = new Scene(rootPane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Classroom App");
        stage.show();
    }
}