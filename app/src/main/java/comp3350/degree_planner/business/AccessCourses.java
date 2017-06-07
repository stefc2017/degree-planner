package comp3350.degree_planner.business;
import java.util.ArrayList;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.persistence.DataAccessStub;

public class AccessCourses {
	private DataAccessStub dataAccess;

	public AccessCourses()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess();
	}

    public ArrayList<Course> getDegreeCourses(int degreeId)
    {
		return dataAccess.getDegreeCourses(degreeId);
    }
}
