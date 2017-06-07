package comp3350.degree_planner.business;
import java.util.ArrayList;
import java.util.List;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;

import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.persistence.DataAccessStub;


import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccessStub;

import static android.support.v7.widget.AppCompatDrawableManager.get;


public class AccessCourses {
	private DataAccessStub dataAccess;

	public AccessCourses() {
		dataAccess = (DataAccessStub) Services.getDataAccess();
	}

	public String getCourses(int degreeId, List<Course> courses) {
		String result = null;
		final int COMP_SCI_ID = 1;

		if (courses != null && degreeId == COMP_SCI_ID) {
			courses.clear();
			courses.add(new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
					1010, "Basic programming concepts."));
			courses.add(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
					1, 1020, "More basic programming concepts."));
			courses.add(new ScienceCourse(3, "Object Orientation", 3.0, 1,
					2150, "Detailed look at proper object oriented programming."));
			for (int i = 0; i < 25; i++) {
				courses.add(new ScienceCourse(3 + i, "Course" + i, 3.0, 1, 1020 + i, "Description"));
			}
		}
		return result;
	}


}



