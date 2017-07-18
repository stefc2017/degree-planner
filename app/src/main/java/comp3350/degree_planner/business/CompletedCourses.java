package comp3350.degree_planner.business;

/*
 * Created by Tiffany Jiang on 2017-06-04.
 * Modified on 2017-06-07.
 */

import java.sql.SQLException;
import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.persistence.DataAccess;

public class CompletedCourses {
    private DataAccess dataAccess;

    public CompletedCourses(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /*
     * Retrieves completed courses for a student
     */
    public List<CourseResult> getCompletedCourses(int studentId) throws Exception {
        return dataAccess.getCourseResultsByStudentId(studentId);
    }

    public List<Course> getCoursesTaken( int studentId ) throws SQLException {
        return dataAccess.getCoursesTaken( studentId );
    }
}
