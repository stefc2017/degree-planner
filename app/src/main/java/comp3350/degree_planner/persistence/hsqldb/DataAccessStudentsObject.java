package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccessStudents;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessStudentsObject implements DataAccessStudents {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessStudentsObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public boolean isValidStudentId(int studentId) throws SQLException {
        boolean studentExists = false;

        cmdString = "Select count(*) as studentCount from Student where id = " + studentId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            studentExists = (rs4.getInt("studentCount") > 0);
        }
        rs4.close();

        return studentExists;
    }
}
