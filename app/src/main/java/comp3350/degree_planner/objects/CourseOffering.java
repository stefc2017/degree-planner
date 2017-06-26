package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A CourseOffering shows a term in which a given course may
 * be taken. There may be multiple CourseOfferings for a
 * single course, showing that a course may be taken in
 * multiple terms.
 */

public class CourseOffering {
    private Course course;
    private TermType termType;

    public CourseOffering(Course course, TermType termType) {
        this.course = course;
        this.termType = termType;
    }

    public Course getCourse() { return this.course; }

    public TermType getTermType() { return this.termType; }

}
