package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A Student represents any student using the app. Students may
 * have a degree they want to work towards.
 */

public class Student {
    private int id;
    private int studentNumber;
    private String name;
    private String email;
    private String password;
    private Degree degree;

    public Student(int id, int studentNumber, String name, String email, String password, Degree degree) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.degree = degree;
    }

    public int getId() { return id; }

    public int getStudentNumber() { return  studentNumber; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public Degree getDegree() { return degree; }
}
