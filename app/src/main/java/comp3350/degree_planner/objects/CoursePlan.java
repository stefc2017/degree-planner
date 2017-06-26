package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Tiffany on 2017-06-04
 * Modified by Kaleigh on 2017-06-07.
 *
 * A CoursePlan is a record of a student planning to take a course
 * in a particular term/year.
 */

public class CoursePlan {
    private int id;
    private Course course;
    private Student student;
    private TermType termType;
    private int year;

    public CoursePlan(int id, Course course, Student student, TermType termType, int year) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.termType = termType;
        this.year = year;
    }

    public Course getCourse() { return this.course; }

    public Student getStudent() { return student; }

    public TermType getTermType() { return termType; }

    public int getYear() { return year; }

    public int getId() { return id; }

    public void setTermType (TermType termType) {
        this.termType = termType;
    }

    public void setYear (int year) { this.year = year; }
}
