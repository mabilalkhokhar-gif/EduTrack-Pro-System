import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;
    private double gpa;

    public Student(int id, String name, int age, String course, double marks, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + course + "," + marks + "," + gpa;
    }
}