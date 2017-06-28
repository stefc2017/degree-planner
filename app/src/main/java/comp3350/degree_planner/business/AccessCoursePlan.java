package comp3350.degree_planner.business;

import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.persistence.DataAccess;

import java.sql.*;

import static org.hsqldb.HsqlDateTime.e;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * This class allows for modifications to course plans
 */

public class AccessCoursePlan {
    private DataAccess dataAccess;

    public AccessCoursePlan(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    //Throws 4 possible exceptions based on 4 errors that may occur:
    //Rethrows any other unpredicted exceptions
    public void addToCoursePlan(int courseId, int studentId, int termTypeId, int year) throws Exception {
        try {
            if (!dataAccess.isValidStudentId(studentId)) {
                throw new StudentDoesNotExistException();
            } else if (!dataAccess.isValidCourseId(courseId)) {
                throw new CourseDoesNotExistException();
            } else if (!dataAccess.isValidTermTypeId(termTypeId)) {
                throw new TermTypeDoesNotExistException();
            } else if (!dataAccess.courseOffered(courseId, termTypeId)) {
                throw new CourseNotOfferedInTermException();
            } else if (dataAccess.coursePlanExists(courseId, studentId, termTypeId, year)) {
                throw new CourseAlreadyPlannedForTermException();
            } else {
                dataAccess.addToCoursePlan(courseId, studentId, termTypeId, year);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean removeFromCoursePlan(int coursePlanId) {
        return dataAccess.removeFromCoursePlan(coursePlanId);
    }

    public boolean moveCourse(int coursePlanId, int newTermTypeId, int newYear) {
        return dataAccess.moveCourse(coursePlanId, newTermTypeId, newYear);
    }
}