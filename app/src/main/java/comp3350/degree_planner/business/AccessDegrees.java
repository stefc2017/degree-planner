package comp3350.degree_planner.business;

import java.sql.SQLException;
import java.util.List;

import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Penny He on 6/4/2017.
 */

public class AccessDegrees {
    private DataAccess dataAccess;

    public AccessDegrees(DataAccess dataAccess){ this.dataAccess = dataAccess; }

    public List<Degree> getAllDegrees() throws SQLException
    {
        return dataAccess.getAllDegrees();
    }

    public Degree getDegreeById(int degreeId) throws SQLException
    {
        return dataAccess.getDegreeById(degreeId);
    }

    public Degree pickDegree(int degreeID) throws SQLException{
        return dataAccess.pickDegree(degreeID);
    }
}