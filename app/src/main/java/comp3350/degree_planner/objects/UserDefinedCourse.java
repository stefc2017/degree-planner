package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 *
 * UserDefinedCourses are courses that are not formally defined by the app.
 * The user gives a course name and the number of credit hours to create
 * one.
 *
 * UserDefinedCourses may be any course not offered by the faculty of science,
 * such as an arts or business course.
 */

public class UserDefinedCourse extends Course{
    private String fullAbbreviation;    // Department abbreviation and course number

    public UserDefinedCourse(int id, String name, double creditHours, String fullAbbreviation) {
        super(id, name, creditHours);
        this.fullAbbreviation = fullAbbreviation;
    }

    public String getFullAbbreviation() { return fullAbbreviation; }
}
