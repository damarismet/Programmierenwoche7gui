package io;

import logic.Course;
import logic.RegularStudent;
import logic.RepeatingStudent;
import logic.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class TagValueDataReader implements CourseDataReader {

    public TagValueDataReader(File fileName) {
    }

    public Optional<Course> readData(File file) {
        ArrayList<Student> studentList = new ArrayList<>();


        String courseId = "";
        String courseName = "";
        String studentName = "";
        String major = "";
        double examGrade = 0;
        boolean repeatStudent = false;
        String key = "";
        String value = "";
        String line = "";

        try {
            Scanner scanner = new Scanner(file);
            line = scanner.nextLine();
            courseId = line.substring((line.indexOf(":")+2));       // Course ID
            line = scanner.nextLine();
            courseName = line.substring((line.indexOf(":")+2));     // Course Name

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                key = line.substring(0,line.indexOf(":"));
                value = line.substring(line.indexOf(":")+2);

                if (key.equals("name")) {
                    studentName = value;
                }
                if (key.equals("major")) {
                    major = value;
                }
                if (key.equals("is_repeating")) {
                    if (value.trim().equals("true")) {
                        repeatStudent = true;
                    } else {
                        repeatStudent = false;
                    }
                }
                if (key.equals("exam-grade")) {
                    examGrade = Double.parseDouble(value);
                    if (repeatStudent) {
                        RepeatingStudent student = new RepeatingStudent(studentName, major, examGrade);
                        studentList.add(student);
                    }
                }
                if (key.equals("pre-grade")) {
                    ArrayList<Double> gradeList = new ArrayList<Double>();
                    String[] tokens = value.split(",");
                    for (String grade : tokens) {
                        gradeList.add(Double.parseDouble(grade));
                    }
                    RegularStudent student = new RegularStudent(studentName, major, examGrade, gradeList);
                    studentList.add(student);
                }


            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        Course course = new Course(courseId, courseName, studentList);
        return Optional.of(course);
    }
}