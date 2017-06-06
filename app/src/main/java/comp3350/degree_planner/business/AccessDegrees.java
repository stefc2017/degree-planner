package comp3350.degree_planner.business;
import java.util.List;
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

    public String getDegrees(List<Degree> degrees)
    {
        String result = null;

        if(degrees != null) {
            degrees.clear();
            degrees.add(new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0));
        }
        return result;
    }

    public Degree getDegreeById(int degreeId){
        Degree d = null;
        if(degreeId == 1){d = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);}
        return d;
    }
}