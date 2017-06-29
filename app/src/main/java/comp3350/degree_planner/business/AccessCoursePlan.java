package comp3350.degree_planner.business;

import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CoursePlanDoesNotExistException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccess;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;

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

    //Adds a course plan
    //Throws 5 different custom exceptions based on the cases specified below,
    // in which error can potentially occur
    //May throw a different unexpected exception, which is caught and rethrown
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

    //Moves a course plan to a different term
    //Throws 4 different exceptions based on the cases specified below, in which error can occur
    public void moveCourse(int coursePlanId, int newTermTypeId, int newYear) throws Exception {
        CoursePlan cp;

        try {
            cp = dataAccess.getCoursePlan(coursePlanId);

            if (cp != null) { //Course Plan exists
                if (!dataAccess.isValidTermTypeId(newTermTypeId)) {
                    throw new TermTypeDoesNotExistException();
                } else if (!dataAccess.courseOffered(cp.getCourse().getId(), newTermTypeId)) {
                    throw new CourseNotOfferedInTermException();
                } else if (dataAccess.coursePlanExists(cp.getCourse().getId(), cp.getStudent().getId(), newTermTypeId, newYear)) {
                    throw new CourseAlreadyPlannedForTermException();
                } else {
                    dataAccess.moveCourse(coursePlanId, newTermTypeId, newYear);
                }
            } else { //Course Plan does not exist - can't modify something that doesn't exist
                throw new CoursePlanDoesNotExistException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //Removes a course plan
    //Throws CoursePlanDoesNotExistException if coursePlanId parameter refers to a nonexistent Course Plan
    public void removeFromCoursePlan(int coursePlanId) throws Exception {
        CoursePlan cp;

        try {
            cp = dataAccess.getCoursePlan(coursePlanId);

            if (cp != null) {
                dataAccess.removeFromCoursePlan(coursePlanId);
            } else {
                throw new CoursePlanDoesNotExistException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Temporary method for developing UI
     * Return a list of Strings and CoursePlans
     * UI renders different ListItem layouts for CoursePlans and section headers(Ex. "Fall 2017")
     */
    public List getCoursePlansAndHeaders(){
        CoursePlan tempCoursePlan;
        List coursePlansAndHeaders = new ArrayList();
        String[] terms = {"Fall 2017", "Winter 2018", "Summer 2018", "Fall 2019"};
        coursePlansAndHeaders.add(terms[0]);

        tempCoursePlan = new CoursePlan(2, new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1, 1010,
                "Basic programming concepts."), new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1),
                new TermType(1, "Fall"), 2017);
        coursePlansAndHeaders.add(tempCoursePlan);

        tempCoursePlan = new CoursePlan(3, new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220"),
                new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(1, "Fall"), 2017);
        coursePlansAndHeaders.add(tempCoursePlan);

        coursePlansAndHeaders.add(terms[1]);
        tempCoursePlan = new CoursePlan(1, new ScienceCourse(3, "Object Orientation", 3.0, 1, 2150,
                "Detailed look at proper object oriented programming."), new Student(1,
                1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(2, "Winter"), 2018);
        coursePlansAndHeaders.add(tempCoursePlan);

        return coursePlansAndHeaders;
    }
}