package comp3350.degree_planner.business;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Retrieves completed courses for a student
 */

import java.util.ArrayList;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

public class AccessCourseResult {
    private DataAccess dataAccess;

    public AccessCourseResult () {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    public AccessCourseResult (DataAccess dataAccess) {
        this.dataAccess = dataAccess;
        dataAccess.open();
    }

    public ArrayList<CourseResult> getCourseResultsByStudentId (int studentId) {
        return dataAccess.getCourseResultsByStudentId(studentId);
    }
}
