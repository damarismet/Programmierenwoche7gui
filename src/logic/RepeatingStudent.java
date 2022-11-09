package logic;

public class RepeatingStudent extends Student {

    public RepeatingStudent() {
    }

    private double examGrade;

    public RepeatingStudent(String name, String major, Double examGrade) {
        super(name, major);
        this.examGrade = examGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }


    public String toString() {
        return getName() + "*";
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {
        double note = examGrade;
        return note;
    }

}
