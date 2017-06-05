package comp3350.degree_planner.business;

import java.util.ArrayList;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 */

public class AccessCoursePlan {
    private DataAccessStub dataAccess;

    public AccessCoursePlan () {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    public boolean addToCoursePlan(int courseId, int studentId, int termTypeId, int year) {
        return dataAccess.addToCoursePlan(courseId, studentId, termTypeId, year);
    }

    public boolean removeFromCoursePlan(int coursePlanId) {
        return dataAccess.removeFromCoursePlan(coursePlanId);
    }

    public boolean moveCourse(int coursePlanId, int newTermTypeId, int newYear) {
        return dataAccess.moveCourse(coursePlanId, newTermTypeId, newYear);
    }

    public ArrayList<CoursePlan> getCoursePlanByStudentId (int studentId) {
        return dataAccess.getCoursePlanByStudentId(studentId);
    }
}
