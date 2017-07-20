package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.persistence.DataAccessDegrees;

/**
 * Created by tiffanyjiang on 2017-07-19.
 */

public class DataAccessDegreesObject implements DataAccessDegrees {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessDegreesObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public Degree getDegreeById(int degreeId) throws SQLException {
        Degree degree;
        int id;
        String name;
        double creditHours, majorCreditHours, gpaRequired;

        degree = null;
        result = null;

        cmdString = "Select * from Degree where ID = " + degreeId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            id = Integer.parseInt(rs2.getString("ID"));
            name = rs2.getString("NAME");
            creditHours = Double.parseDouble(rs2.getString("CREDIT_HOURS"));
            majorCreditHours = Double.parseDouble(rs2.getString("MAJOR_CREDIT_HOURS"));
            gpaRequired = Double.parseDouble(rs2.getString("GPA_REQUIRED"));
            degree = new Degree(id, name, creditHours, majorCreditHours, gpaRequired);
        }

        rs2.close();

        return degree;
    }
}
