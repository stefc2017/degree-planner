package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Rating {
    private Student author;
    private Course courseRated;
    private RatingType ratingGiven;
    private String comment;

    public Rating(Student author, Course courseRated, RatingType ratingGiven, String comment) {
        this.author = author;
        this.courseRated = courseRated;
        this.ratingGiven = ratingGiven;
        this.comment = comment;
    }

    public Student getAuthor() {
        return author;
    }

    public Course getCourseRated() {
        return courseRated;
    }

    public RatingType getRatingGiven() {
        return ratingGiven;
    }

    public String getComment() {
        return comment;
    }
}
