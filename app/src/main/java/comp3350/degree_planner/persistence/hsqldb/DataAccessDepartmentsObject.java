package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.persistence.DataAccessDepartments;

/**
 * Created by tiffanyjiang on 2017-07-19.
 */

public class DataAccessDepartmentsObject implements DataAccessDepartments {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessDepartmentsObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        Department department;
        int id;
        String name;
        String abbreviation;

        department = null;
        result = null;

        cmdString = "Select * from DEPARTMENT where ID = " + departmentId;
        rs6 = st1.executeQuery(cmdString);

        while (rs6.next()) {
            id = rs6.getInt("ID");
            name = rs6.getString("NAME");
            abbreviation = rs6.getString("ABBREVIATION");
            department = new Department(id, name, abbreviation);
        }

        return department;
    }
}
