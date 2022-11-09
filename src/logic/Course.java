package logic;

import java.util.ArrayList;

public class Course {

    private final String courseId;
    private final String courseName;
    private final ArrayList<Student> students;


    public Course(String courseId, String courseName, ArrayList<Student> students) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.students = students;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}