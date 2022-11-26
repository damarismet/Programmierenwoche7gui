package gui;


import io.MajorMapReader;
import javafx.geometry.Orientation;
import javafx.scene.control.*;

import javafx.scene.layout.StackPane;

import java.util.Map;


public class MainPane extends StackPane {

    MajorMapReader mapReader = new MajorMapReader("src/io/major-map.txt");
    Map<String, String> majorMap = mapReader.readMajorMap();


    public MainPane() {

        StateModel stateModel = new StateModel();

        GraphicsPane graphicsPane = new GraphicsPane(stateModel);
        ControlPane controlPane = new ControlPane(majorMap, stateModel);

        // Layout the components
        //-----------------------
        final SplitPane verticalSplitPane = new SplitPane();
        verticalSplitPane.setOrientation(Orientation.HORIZONTAL);
        verticalSplitPane.setDividerPosition(0, 0.5);
        verticalSplitPane.getItems().addAll(controlPane, graphicsPane);
        getChildren().add(verticalSplitPane);
    }
}