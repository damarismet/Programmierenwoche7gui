package gui;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import logic.Student;

import java.util.List;

public class GraphicsPane extends Pane {


    public GraphicsPane(StateModel stateModel) {


        CheckBox checkBox = new CheckBox("Sort by Grade");
        //checkBox.setIndeterminate(false);
        checkBox.setSelected(stateModel.isSortByGrade());

        Pane graphic = new Pane();
        //graphic.setStyle("-fx-background-color: white");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(checkBox, graphic);


        checkBox.setOnAction(event -> stateModel.setSortByGrade(checkBox.isSelected()));
        stateModel.addObserver(() -> drawGraphic(stateModel, checkBox));

        widthProperty().addListener(observable -> drawGraphic(stateModel, checkBox));
        heightProperty().addListener(observable -> drawGraphic(stateModel, checkBox));

        getChildren().add(vBox);

    }


    private void drawGraphic(StateModel stateModel, CheckBox checkBox) {

        getChildren().clear();
        getChildren().add(checkBox);


        if (stateModel.getCourse() != null) {
            List<Student> students = stateModel.getCourse().getStudents();

            double barWidth = getWidth() / students.size();
            final double GRADE_THRESHOLD = 4;

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                double barHeight = student.getFinalGrade(stateModel.getPreGradeFactor()) / 6.0 * getHeight();
                double x = i * barWidth;
                double y = getHeight() - barHeight; // die y-Koordinaten von JavaFX stehen auf dem Kopf


                Rectangle gradeBar = new Rectangle(x, y, barWidth, barHeight);
                gradeBar.setStroke(Color.WHITE);

                if (student.getFinalGrade(stateModel.getPreGradeFactor()) >= GRADE_THRESHOLD) {
                    gradeBar.setFill(Color.OLIVEDRAB);
                } else {
                    gradeBar.setFill(Color.INDIANRED);
                }


                getChildren().add(gradeBar);


                Text nameText = new Text(student.getName());
                nameText.getTransforms().add(new Translate(x + barWidth / 2, getHeight() - 10));
                nameText.getTransforms().add(new Rotate(-90));
                getChildren().add(nameText);
            }
            Line line = new Line(0, getHeight() - (GRADE_THRESHOLD * (getHeight() / 6.0)),
                   getWidth(), getHeight() - (GRADE_THRESHOLD * (getHeight() / 6.0)));

            line.setStroke(Color.RED);
            getChildren().add(line);
        }

    }


}

