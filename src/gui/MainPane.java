package gui;

import io.CourseDataReader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import logic.Course;
import logic.Student;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class MainPane extends Pane {
    public MainPane(Map<String, String> majorMap) {

        // Create the components
        Button loadButton =  new Button("Load Data...");
        Label numberLabel = new Label("Number of students: ");
        Label numberLabelValue = new Label("Pre Grade Factor: ");
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        Slider preGradeFactorSlider = new Slider(0,100,30);

// Define the logic
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File dataFile = fileChooser.showOpenDialog(null);
            if (dataFile != null) {
                Optional<CourseDataReader> dataReader = CourseDataReader.getReader(dataFile);
                if (dataReader.isPresent()) {
                    Optional<Course> c = dataReader.get().readData(dataFile);

                    if (c.isPresent()) {
                        Course course;
                        course = c.get();

                        for (Student student : course.getStudents()) {
                            String major;
                            if (majorMap.containsKey(student.getMajor())) {
                                major = majorMap.get(student.getMajor());
                            } else {
                                major = student.getMajor();
                            }
                            String studentText = (student + ": "+ Math.round(student.getFinalGrade(preGradeFactorSlider.getValue()) * 10) / 10.0);
                            textArea.setText(studentText);
                            numberLabel.setText(String.valueOf(course.getStudents().size()));


                            refreshText(course, numberLabelValue, textArea, preGradeFactorSlider.getValue());
                        }

                    } else {
                        Alert alert = new Alert (Alert.AlertType.ERROR, "Unable to read course data from file " + dataFile);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert (Alert.AlertType.ERROR, "File format unknown for file " + dataFile);
                    alert.showAndWait();
                } }
        });
// For a slider, we need to attach an event listener to the value property of the slider
        preGradeFactorSlider.valueProperty().addListener(observable -> {
            //[refreshText...]
        });
// Layout the components
        VBox mainPane = new VBox();
        mainPane.getChildren().add(loadButton);
        mainPane.getChildren().add(numberLabel);
        mainPane.getChildren().add(numberLabelValue);
        mainPane.getChildren().add(preGradeFactorSlider);
        //[...add and layout the other components]
        this.getChildren().add(mainPane);
    }

    private void refreshText(Course course, Label numberLabelValue, TextArea textArea, double value) {

    }
}