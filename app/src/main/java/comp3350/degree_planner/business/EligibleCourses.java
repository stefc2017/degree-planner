package comp3350.degree_planner.business;

import java.sql.SQLException;
import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Matthew Provencher on 7/10/2017.
 */

public class EligibleCourses {
    private DataAccess dataAccess;

    public EligibleCourses(DataAccess dataAccess) { this.dataAccess = dataAccess; }

    public List<Course> getEligibleRequiredCourse(int studentId, int degreeId) throws SQLException {
        return dataAccess.getEligibleRequiredCourse( studentId, degreeId);
    }
}
