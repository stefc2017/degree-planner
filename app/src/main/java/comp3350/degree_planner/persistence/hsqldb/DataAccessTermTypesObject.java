package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccessTermTypes;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessTermTypesObject implements DataAccessTermTypes {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessTermTypesObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public boolean isValidTermTypeId(int termTypeId) throws SQLException {
        boolean termTypeExists = false;

        cmdString = "Select count(*) as termTypeCount from Term_Type where id = " + termTypeId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            termTypeExists = (rs4.getInt("termTypeCount") > 0);
        }
        rs4.close();

        return termTypeExists;
    }

    public int getTermTypeIdByName(String termType) throws SQLException {
        int termTypeId = -1;

        cmdString = "Select * from Term_Type where Season = " + "'" + termType + "'";
        rs2 = st1.executeQuery(cmdString);

        while(rs2.next()) {
            termTypeId = rs2.getInt("ID");
        }
        rs2.close();

        return termTypeId;
    }
}
