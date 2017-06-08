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
    private int courseId;
    private int studentId;
    private int termTypeId;
    private int year;
    public static int idCount = 0;

    public CoursePlan(int courseId, int studentId, int termTypeId, int year) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.termTypeId = termTypeId;
        this.year = year;
        id = idCount;
        idCount++;
    }

    public int getCourseId() { return courseId; }

    public int getStudentId() { return studentId; }

    public int getTermTypeId() { return termTypeId; }

    public int getYear() { return year; }

    public int getId() {
        return id;
    }

    public void setTermTypeId (int termTypeId) {
        this.termTypeId = termTypeId;
    }

    public void setYear (int year) {
        this.year = year;
    }
}
