package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 *
 * A DegreeCourse is a record of a course that is required for a degree,
 * or a course that may be taken as an elective to fulfill a certain
 * number of required credit hours in the area of study for the degree.
 */

public class DegreeCourse {
    int degreeId;
    int courseId;
    int courseTypeId;

    public DegreeCourse(int degreeId, int courseId, int courseTypeId) {
        this.degreeId = degreeId;
        this.courseId = courseId;
        this.courseTypeId = courseTypeId;
    }

    public int getDegreeId(){ return degreeId; }
    public int getCourseId(){ return courseId; }
}
