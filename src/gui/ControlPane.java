package gui;

import io.CourseDataReader;
import io.MajorMapReader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import logic.Course;
import logic.Student;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ControlPane extends StackPane {

    MajorMapReader mapReader = new MajorMapReader("src/io/major-map.txt");
    Map<String, String> majorMap = mapReader.readMajorMap();
    Course course;

    public ControlPane(Map<String, String> majorMap, StateModel stateModel) {
        this.majorMap = majorMap;



        // Create the components
        Button loadButton = new Button("Load Data...");
        Label numberLabel = new Label();
        Label numberLabelValue = new Label("0");
        HBox studentcountBox= new HBox(numberLabel, numberLabelValue);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        Label factorLabel = new Label("Pre Grade Factor[%]: ");
        Slider preGradeFactorSlider = new Slider(0, 100, 30);
        preGradeFactorSlider.setShowTickMarks(true);
        preGradeFactorSlider.setShowTickLabels(true);
        preGradeFactorSlider.setMajorTickUnit(10);

// Define the logic
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File dataFile = fileChooser.showOpenDialog(null);
            if (dataFile != null) {
                Optional<CourseDataReader> dataReader = CourseDataReader.getReader(dataFile);
                if (dataReader.isPresent()) {
                    Optional<Course> c = dataReader.get().readData(dataFile);

                    if (c.isPresent()) {
                        course = c.get();
                        stateModel.setCourse(course);

                        //refreshText(course,numberLabel, numberLabelValue, textArea, preGradeFactorSlider.getValue());


                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to read course data from file " + dataFile);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "File format unknown for file ");
                alert.showAndWait();
            }
        });

// For a slider, we need to attach an event listener to the value property of the slider
        preGradeFactorSlider.valueProperty().addListener(observable -> {
            //refreshText(course,numberLabel, numberLabelValue, textArea, preGradeFactorSlider.getValue());
            stateModel.setPreGradeFactor(preGradeFactorSlider.getValue()/100);

        });
stateModel.addObserver(() -> {
            refreshText(course,numberLabel, numberLabelValue, textArea, preGradeFactorSlider.getValue());
        });


// Layout the components
        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(loadButton, studentcountBox, textArea,factorLabel, preGradeFactorSlider);
        this.getChildren().add(mainPane);


    }

    private void refreshText(Course course,Label numberLabel, Label numberLabelValue, TextArea textArea, double preGradeFactorSlider) {

        textArea.clear();
        double preGradeFactor = preGradeFactorSlider / 100;

        for (Student student : course.getStudents()) {
            String major;
            if (majorMap.containsKey(student.getMajor())) {
                major = majorMap.get(student.getMajor());
            } else {
                major = student.getMajor();
            }
            String studentText = "The final grade for " + student + " (" + major + ") is: " +student.getFinalGrade(preGradeFactor)+ "\n";
            textArea.appendText(studentText);
            numberLabelValue.setText(String.valueOf(course.getStudents().size()));

            String studentLabel = "Number of students: ";
            numberLabel.setText(studentLabel);

        }


    }

}