package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import logic.Student;

import java.util.ArrayList;

public class GraphicsPane extends Pane {


    private ArrayList<Student> students;
    double preGradeFactor;



    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        drawGraphic();
    }

    public void setPreGradeFactor(double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
        drawGraphic();

    }
    public void drawGraphic(){

    getChildren().clear();

    double barWidth = getWidth() / students.size();
    for (int i = 0; i < students.size(); i++) {
        Student student = students.get(i);
        double barHeight = student.getFinalGrade(preGradeFactor) / 6.0 * getHeight();
        double x = i * barWidth;
        double y = getHeight() - barHeight; // die y-Koordinaten von JavaFX stehen auf dem Kopf
        Rectangle gradeBar = new Rectangle(x , y, barWidth, barHeight);
        if (student.getFinalGrade(preGradeFactor) > 3.99) {
            gradeBar.setFill(Color.OLIVEDRAB);
        } else {
            gradeBar.setFill(Color.INDIANRED);
        }
        getChildren().add(gradeBar);

        Text nameText = new Text(student.getName());
        nameText.getTransforms().add(new Translate(x + barWidth/2, getHeight() - 10));
        nameText.getTransforms().add(new Rotate(-90));
        getChildren().add(nameText);

        }



    }
}
