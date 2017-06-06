package comp3350.degree_planner.business;

import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.persistence.DataAccessStub;

public class AccessCourses
{
	private DataAccessStub dataAccess;

	public AccessCourses()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess();
	}

    public String getCourses()
    {
        return null;
    }
}
