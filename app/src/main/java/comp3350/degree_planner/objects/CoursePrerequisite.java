package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 */

public class CoursePrerequisite {
    private int courseId;
    private int prereqCourseId;

    public CoursePrerequisite(int courseId, int prereqCourseId) {
        this.courseId = courseId;
        this.prereqCourseId = prereqCourseId;
    }
}
