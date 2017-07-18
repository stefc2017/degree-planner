package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccessCourses;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessCoursesObject implements DataAccessCourses {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String dbName;
    private String dbType;

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
}
