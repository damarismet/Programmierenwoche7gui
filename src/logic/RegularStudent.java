package logic;

import java.util.ArrayList;

public class RegularStudent extends Student {

    public RegularStudent() {
    }

    private ArrayList<Double> gradeList;
    private double examGrade;

    public RegularStudent(String name, String major, Double examGrade, ArrayList<Double> gradeList) {
        super(name, major);
        this.gradeList = gradeList;
        this.examGrade = examGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public ArrayList<Double> getGradeList() {
        return gradeList;
    }

    private double getAverage() {
        double lowestgrade;
        double summe;
        double average;
        summe = 0;
        lowestgrade = gradeList.get(0);
        for (Double aDouble : gradeList) {
            if (lowestgrade > aDouble) {
                lowestgrade = aDouble;
            }
            summe = summe + aDouble;
        }
        summe = summe - lowestgrade;
        average = summe / ((gradeList.size() - 1));
        return average;
    }

    public String toString() {
        return getName();
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {
        double note = Math.round((getAverage() * preGradeFactor + examGrade * (1 - preGradeFactor))*10)/10.0;
        return note;
    }
}
