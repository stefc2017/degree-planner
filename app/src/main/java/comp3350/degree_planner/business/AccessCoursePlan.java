package comp3350.degree_planner.business;

import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Modifications to course plan
 */

public class AccessCoursePlan {
    private DataAccess dataAccess;

    public AccessCoursePlan(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
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

//    public ArrayList<CoursePlan> getCoursePlanByStudentId (int studentId) {
//        return dataAccess.getCoursePlanByStudentId(studentId);
//    }
}
