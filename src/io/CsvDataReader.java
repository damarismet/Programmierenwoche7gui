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

public class CsvDataReader implements CourseDataReader{

    public CsvDataReader() {
    }

    public Optional<Course> readData(File file) {
        ArrayList<Student> studentList = new ArrayList<>();


        String courseId = null;
        String courseName = null;
        try {
            Scanner scanner = new Scanner(file);

            courseId = scanner.nextLine();
            courseName = scanner.nextLine();
            while (scanner.hasNextLine()) {

                ArrayList<Double> listOfDouble = new ArrayList<>();
                String text = scanner.nextLine();
                String[] tokens = text.split(",");

                String studentName = tokens[0];
                String major = tokens[1].trim();
                double examGrade = Double.parseDouble(tokens[3]);

                String isRepeating = tokens[2].trim();
                for (int i = 3; i < tokens.length; i++) {

                    listOfDouble.add(Double.parseDouble(tokens[i]));
                }

                Student student;
                if (isRepeating.equals("r")) {
                    student = new RepeatingStudent(studentName, major, examGrade);
                } else {
                    ArrayList<Double> studentGrades = new ArrayList<>();
                    for (int i = 4; i < tokens.length; i++) {
                        double grade = Double.parseDouble(tokens[i]);
                        studentGrades.add(grade);
                    }
                    student = new RegularStudent(studentName, major, examGrade, studentGrades);
                }
                studentList.add(student);
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        Course course = new Course(courseId, courseName, studentList);
        return Optional.of(course);
    }
}