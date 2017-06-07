package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A Course Prerequisite is a record that a course is
 * required to have been taken before taking a certain course.
 */

public class CoursePrerequisite {
    private int courseId;
    private int prereqCourseId;

    public CoursePrerequisite(int courseId, int prereqCourseId) {
        this.courseId = courseId;
        this.prereqCourseId = prereqCourseId;
    }

    public int getCourseId(){ return courseId; }

    public int getPrereqCourseId(){ return prereqCourseId; }
}
