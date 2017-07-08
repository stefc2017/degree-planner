package comp3350.degree_planner.business;
import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.TermType;

import comp3350.degree_planner.persistence.DataAccess;

public class AccessCourses {
	private DataAccess dataAccess;

	public AccessCourses(DataAccess dataAccess) { this.dataAccess = dataAccess; }

    public List<Course> getDegreeCourses(int degreeId) { return dataAccess.getDegreeCourses(degreeId); }

	public Course getCourseById(int courseId) { return dataAccess.getCourseById(courseId); }

	public List<CourseOffering> getAllCourseOfferings(){return dataAccess.getAllCourseOfferings();}

	public List<CourseOffering> getCourseOfferingsByTerm(TermType term){ return dataAccess.getCourseOfferingsByTerm(term); }
}



