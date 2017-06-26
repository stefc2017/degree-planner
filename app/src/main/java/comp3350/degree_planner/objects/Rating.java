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
    private Student student;
    private Course course;
    private RatingType ratingType;
    private String comment;

    public Rating(int id, Student student, Course course, RatingType ratingType, String comment) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.ratingType = ratingType;
        this.comment = comment;
    }

    public int getId() { return id; }

    public Student getStudent() { return student; }

    public Course getCourse() { return course; }

    public RatingType getRatingType() { return ratingType; }

    public String getComment() {
        return comment;
    }
}
