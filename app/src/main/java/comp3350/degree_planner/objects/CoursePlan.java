package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A CoursePlan is a record of a student planning to take a course
 * in a particular term/year.
 */

public class CoursePlan {
    private int courseId;
    private int studentId;
    private int termTypeId;
    private int year;

    public CoursePlan(int courseId, int studentId, int termTypeId, int year) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.termTypeId = termTypeId;
        this.year = year;
    }

    public int getCourseId() { return courseId; }

    public int getStudentId() { return studentId; }

    public int getTermTypeId() { return termTypeId; }

    public int getYear() { return year; }
}
