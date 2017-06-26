package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A DegreeCourse is a record of a course that is required for a degree,
 * or a course that may be taken as an elective to fulfill a certain
 * number of required credit hours in the area of study for the degree.
 */

public class DegreeCourse {
    private Degree degree;
    private Course course;
    private DegreeCourseType degreeCourseType;

    public DegreeCourse(Degree degree, Course course, DegreeCourseType degreeCourseType) {
        this.degree = degree;
        this.course = course;
        this.degreeCourseType = degreeCourseType;
    }

    public Degree getDegree() { return degree; }

    public Course getCourse() { return course; }

    public DegreeCourseType getDegreeCourseType() { return degreeCourseType; }
}
