package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 */

public class CourseOffering {
    private int courseId;
    private int termTypeId;

    public CourseOffering(int courseId, int termTypeId) {
        this.courseId = courseId;
        this.termTypeId = termTypeId;
    }
}