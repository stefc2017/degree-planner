package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Student {
    private String name;
    private int studentNumber;
    private String email;
    private String password;        // Make password a hash?
    private double gpa;

    private Course[] coursesPassed;
    private Course[] coursesFailed;
    private DegreePlan degreePlan;

    public Student(String name, int studentNumber, String email, String password) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getGpa() {
        return gpa;
    }

    public Course[] getCoursesPassed() {
        return coursesPassed;
    }

    public Course[] getCoursesFailed() {
        return coursesFailed;
    }

    public DegreePlan getDegreePlan() {
        return degreePlan;
    }
}
