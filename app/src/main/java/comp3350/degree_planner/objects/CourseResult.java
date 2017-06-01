package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 *
 * A CourseResult is a simple object that holds a grade for a course completed as
 * well as the student who completed it, as IDs.
 */

public class CourseResult {
    private int id;         // Id of the object, since a student may take a course more than once
    private int courseId;
    private int studentId;
    private int gradeId;

    public CourseResult(int id, int courseId, int studentId, int gradeId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.gradeId = gradeId;
    }

    public int getId() { return id; }
}
