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
    private int courseId;
    private int termTypeId;

    public CourseOffering(int courseId, int termTypeId) {
        this.courseId = courseId;
        this.termTypeId = termTypeId;
    }

    public int getCourseId() { return courseId; }

    public int getTermTypeId() { return termTypeId; }
}
