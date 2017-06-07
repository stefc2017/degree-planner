package comp3350.degree_planner.business;
import java.util.ArrayList;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Penny He on 6/4/2017.
 */

public class AccessDegrees {
    private DataAccessStub dataAccess;

    public AccessDegrees()
    {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    public ArrayList<Degree> getAllDegrees()
    {
        return dataAccess.getAllDegrees();
    }

    public Degree getDegreeById(int degreeId)
    {
        return dataAccess.getDegreeById(degreeId);
    }
}