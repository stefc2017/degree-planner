package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccessCourseOfferings;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessCourseOfferingsObject implements DataAccessCourseOfferings {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessCourseOfferingsObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public boolean courseOffered(int courseId, int termTypeId) throws SQLException {
        boolean validTerm = false;

        //For user-defined courses, let the user freely enter the the term and year
        cmdString = "Select is_user_defined from Course where id = " + courseId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            validTerm = rs4.getBoolean("is_user_defined");
        }
        rs4.close();

        if (!validTerm) {
            //Is the course historically offered in this term?
            cmdString = "Select count(*) as courseOfferingCount from Course_Offering where course_id = " + courseId + " and term_type_id = " + termTypeId;
            rs4 = st2.executeQuery(cmdString);
            while (rs4.next()) {
                validTerm = (rs4.getInt("courseOfferingCount") > 0);
            }
            rs4.close();
        }

        return validTerm;
    }
}
