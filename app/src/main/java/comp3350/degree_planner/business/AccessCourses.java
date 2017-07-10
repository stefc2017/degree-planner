package comp3350.degree_planner.business;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.TermType;

import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;

public class AccessCourses {
	private DataAccess dataAccess;
	private List<Course> courses = new ArrayList<>();
	int id = 6;
	public AccessCourses(DataAccess dataAccess) { this.dataAccess = dataAccess; }

    public List<Course> getDegreeCourses(int degreeId) { return dataAccess.getDegreeCourses(degreeId); }

	public List<Course> getAllUserDefinedCourses() {
		courses.add(new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220"));
		courses.add(new UserDefinedCourse(6, "Language and Culture", 3.0, "ANTH 2370"));
		return courses;
	}

	public void removeUserDefinedCourse(int courseId) {
		for(Course c : courses){
			if(c.getId() == courseId){
				courses.remove(c);
			}
		}
	}

	public void addUserDefinedCourse(String name, double creditHours, String abbreviation){
		courses.add(new UserDefinedCourse(++id, name, creditHours, abbreviation));
	}

	public Course getCourseById(int courseId) { return dataAccess.getCourseById(courseId); }

	public List<CourseOffering> getAllCourseOfferings(){return dataAccess.getAllCourseOfferings();}

	public List<CourseOffering> getCourseOfferingsByTerm(TermType term){ return dataAccess.getCourseOfferingsByTerm(term); }
}



