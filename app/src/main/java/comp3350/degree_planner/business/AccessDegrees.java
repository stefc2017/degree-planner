package comp3350.degree_planner.business;

import java.util.List;

import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Penny He on 6/4/2017.
 */

public class AccessDegrees {
    private DataAccess dataAccess;

    public AccessDegrees(DataAccess dataAccess){ this.dataAccess = dataAccess; }


    public List<Degree> getAllDegrees()
    {
        return dataAccess.getAllDegrees();
    }

    public Degree getDegreeById(int degreeId)
    {
        return dataAccess.getDegreeById(degreeId);
    }
}