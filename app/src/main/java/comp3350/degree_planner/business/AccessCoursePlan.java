package comp3350.degree_planner.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccess;

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

    public int addToCoursePlan(int courseId, int studentId, int termTypeId, int year) {
        return dataAccess.addToCoursePlan(courseId, studentId, termTypeId, year);
    }

    public boolean removeFromCoursePlan(int coursePlanId) {
        return dataAccess.removeFromCoursePlan(coursePlanId);
    }

    public boolean moveCourse(int coursePlanId, int newTermTypeId, int newYear) {
        return dataAccess.moveCourse(coursePlanId, newTermTypeId, newYear);
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
