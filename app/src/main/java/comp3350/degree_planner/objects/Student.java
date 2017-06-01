package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Student {
    private int id;
    private int studentNumber;
    private String name;
    private String email;
    private String password;        // Make password a hash?
    private int degreeId;

    public Student(int id, int studentNumber, String name, String email, String password, int degreeId) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.degreeId = degreeId;
    }

    public int getId() {
        return id;
    }

    public int getStudentNumber() { return  studentNumber; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }
}
