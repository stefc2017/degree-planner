package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccessCourses;
import comp3350.degree_planner.persistence.DataAccessDepartments;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessCoursesObject implements DataAccessCourses {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessCoursesObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public boolean isValidCourseId(int courseId) throws SQLException {
        boolean courseExists = false;

        cmdString = "Select count(*) as courseCount from Course where id = " + courseId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            courseExists = (rs4.getInt("courseCount") > 0);
        }
        rs4.close();

        return courseExists;
    }

    public Course getCourseById(int courseId) throws SQLException {
        Course course;
        int id;
        String name;
        double creditHours;
        int courseNumber;
        String description;
        int departmentId;
        String fullAbbreviation;
        Boolean isUserDefined;
        Department department;

        course = null;
        result = null;

        DataAccessDepartments dataAccessDepartments = Services.getDataAccessDepartments();

        cmdString = "Select * from Course where ID = " + courseId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            id = Integer.parseInt(rs2.getString("ID"));
            name = rs2.getString("NAME");
            creditHours = Double.parseDouble(rs2.getString("CREDIT_HOURS"));
            departmentId = rs2.getInt("DEPARTMENT_ID");
            courseNumber = rs2.getInt("COURSE_NUMBER");
            description = rs2.getString("DESCRIPTION");
            fullAbbreviation = rs2.getString("FULL_ABBREVIATION");
            isUserDefined = Boolean.parseBoolean(rs2.getString("IS_USER_DEFINED"));

            if (isUserDefined) {
                course = new UserDefinedCourse(id, name, creditHours, fullAbbreviation);
            } else {
                department = dataAccessDepartments.getDepartmentById(departmentId);
                course = new ScienceCourse(id, name, creditHours, department, courseNumber, description);
            }
        }

        rs2.close();

        return course;
    }
}
