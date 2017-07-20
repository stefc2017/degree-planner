package comp3350.degree_planner.persistence;

import java.sql.SQLException;

import comp3350.degree_planner.objects.Course;

/**
 * Created by tiffanyjiang on 2017-07-17.
 */

public interface DataAccessCourses {
    boolean isValidCourseId (int courseId) throws SQLException;

    Course getCourseById(int courseId) throws SQLException;
}
