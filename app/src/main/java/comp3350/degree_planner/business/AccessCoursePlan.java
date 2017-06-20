package comp3350.degree_planner.business;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;

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
     * Return a list of Strings and Courses
     * UI renders different ListItem layouts for Courses and section headers(Ex. "Fall 2017")
     */
    public ArrayList getCoursePlansAndHeaders(){
        Course tempScienceCourse, tempUserDefinedCourse;
        ArrayList coursePlansAndHeaders = new ArrayList();
        String[] terms = {"Fall 2017", "Winter 2018", "Summer 2018", "Fall 2019"};
        coursePlansAndHeaders.add(terms[0]);
        tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
                1010, "Basic programming concepts.");
        coursePlansAndHeaders.add(tempScienceCourse);
        tempUserDefinedCourse = new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220");
        coursePlansAndHeaders.add(tempUserDefinedCourse);
        coursePlansAndHeaders.add(terms[1]);
        tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts.");
        coursePlansAndHeaders.add(tempScienceCourse);
        tempScienceCourse = new ScienceCourse(7, "Programming Practices", 3.0,
                1, 2160, "123");
        coursePlansAndHeaders.add(tempScienceCourse);
        coursePlansAndHeaders.add(terms[2]);
        tempScienceCourse = new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming.");
        coursePlansAndHeaders.add(tempScienceCourse);
        tempScienceCourse = new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                3350, "Good software development practices.");
        coursePlansAndHeaders.add(tempScienceCourse);

        coursePlansAndHeaders.add(terms[3]);
        for(int i = 0; i < 20; i++){
            coursePlansAndHeaders.add(new ScienceCourse(5+i, "Course " + i, 3.0, 1,
                    1010+i, "description"));
        }
        return coursePlansAndHeaders;
    }
}
