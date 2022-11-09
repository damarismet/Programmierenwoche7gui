package logic;
public class Student {

    public Student() {
    }

    private String name;
    private String major;


    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public double getFinalGrade(double preGradeFactor) {
        return 0;
    }

}