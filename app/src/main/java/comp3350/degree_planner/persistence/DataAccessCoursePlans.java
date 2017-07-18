package comp3350.degree_planner.persistence;

import java.sql.SQLException;
import java.util.List;

import comp3350.degree_planner.objects.CoursePlan;

/**
 * Created by tiffanyjiang on 2017-07-17.
 */

public interface DataAccessCoursePlans {
    void addToCoursePlan (int courseId, int studentId, int termTypeId, int year) throws SQLException;

    boolean coursePlanExists (int courseId, int studentId, int termTypeId, int year) throws SQLException;

    void moveCourse (int coursePlanId, int newTermTypeId, int newYear) throws SQLException;

    void removeFromCoursePlan (int coursePlanId) throws SQLException;

    CoursePlan getCoursePlan (int courseId, int studentId, int termTypeId, int year) throws SQLException;

    CoursePlan getCoursePlan (int coursePlanId) throws SQLException;

    List<CoursePlan> getCoursePlansByStudentId (int studentId) throws SQLException;
}
