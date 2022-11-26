package gui;

import logic.Course;
import logic.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StateModel {
    private Course course;
    private double preGradeFactor;
    private boolean sortByGrade;



    List<StateObserver> observers;
    public StateModel() {
        observers = new ArrayList<>();
    }

    //...Getter methods
    public Course getCourse() {
        return course;
    }

    public double getPreGradeFactor() {
        return preGradeFactor;
    }

    public boolean isSortByGrade() {
        return sortByGrade;
    }


    public void setCourse(Course course) {
        this.course = course;
        refreshStudentSort();
        sendStateChangedEvent();
    }
      //...other 2 Setter methods


    public void setPreGradeFactor(double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
        refreshStudentSort();
        sendStateChangedEvent();
    }

    public void setSortByGrade(boolean sortByGrade){
        this.sortByGrade= sortByGrade;
        refreshStudentSort();
        sendStateChangedEvent();
    }


    private void refreshStudentSort(){
        if(course!= null){
            if (sortByGrade){
                course.getStudents().sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return Double.compare(s1.getFinalGrade(preGradeFactor), s2.getFinalGrade(preGradeFactor));
                    }
                });
            }
            else{
                course.getStudents().sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getName().compareTo(s2.getName());
                    }
                });
            }
        }

            }


    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }
    private void sendStateChangedEvent() {
        for (StateObserver observer : observers) {
            observer.stateChanged();
        }
    }
}
