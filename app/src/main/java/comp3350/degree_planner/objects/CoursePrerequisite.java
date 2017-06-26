package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A Course Prerequisite is a record that a course is
 * required to have been taken before taking a certain course.
 */

public class CoursePrerequisite {
    private Course course;
    private Course prereqCourse;

    public CoursePrerequisite(Course course, Course prereqCourse) {
        this.course = course;
        this.prereqCourse = prereqCourse;
    }

    public Course getCourse(){ return this.course; }

    public Course getPrereqCourse(){ return prereqCourse; }
}
