package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A student may give a Rating to a course once they have completed it.
 * A rating includes a rating type (giving a numeric value) as well as
 * a comment.
 */

public class Rating {
    private int id;
    private int studentId;
    private int courseId;
    private int ratingTypeId;
    private String comment;

    public Rating(int id, int studentId, int courseId, int ratingTypeId, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.ratingTypeId = ratingTypeId;
        this.comment = comment;
    }

    public int getId() { return id; }

    public int getStudentId() { return studentId; }

    public int getCourseId() { return courseId; }

    public int getRatingTypeId() { return ratingTypeId; }

    public String getComment() {
        return comment;
    }
}
