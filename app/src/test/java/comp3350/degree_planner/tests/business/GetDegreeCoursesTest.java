package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.DegreeCourseType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Penny He on 6/7/2017.
 *
 * Unit tests for getting required course for a degree
 */

public class GetDegreeCoursesTest {
    private AccessCourses degreeReqCourses;

    // Sets up test data with the entries we need for all tests below
    @Before
    public void setUp() {
        final DataAccess testData = new DataAccessStub() {
            private List<Course> courses;
            private List<Degree> degrees;
            private List<DegreeCourseType> degreeCourseTypes;
            private List<ScienceCourse> scienceCourses;
            private List<UserDefinedCourse> userDefinedCourses;
            private List<DegreeCourse> degreeCourses;

            @Override
            public void open(String dbName){
                ScienceCourse tempScienceCourse;
                UserDefinedCourse tempUserDefinedCourse;

                // Create Courses

                courses = new ArrayList<Course>();
                scienceCourses = new ArrayList<ScienceCourse>();
                userDefinedCourses = new ArrayList<UserDefinedCourse>();

                tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
                        1010, "Basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                // Create Degrees

                degrees = new ArrayList<Degree>();
                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
                degrees.add(degree);

                // Create types of degree courses

                degreeCourseTypes = new ArrayList<DegreeCourseType>();
                degreeCourseTypes.add(new DegreeCourseType(1, "Required"));
                degreeCourseTypes.add(new DegreeCourseType(2, "Elective for Major"));

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));
            }

            @Override
            public List<Course> getDegreeCourses(int degreeId){
                final int REQUIRED_COURSE = 1;
                ArrayList<Course> reqCourseList = new ArrayList<Course>();

                for( DegreeCourse course : degreeCourses ){
                    if( course.getDegree().getId() == degreeId && course.getDegreeCourseType().getId() == REQUIRED_COURSE){
                        reqCourseList.add( getCourseById( course.getCourse().getId() ) );
                    }
                }

                return reqCourseList;
            }

            @Override
            public Course getCourseById(int courseId) {
                Course result = null;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        result = courses.get(i);
                        break;
                    }
                }

                return result;
            }
        };

        degreeReqCourses = new AccessCourses(testData);
        testData.open(Main.dbName);
    }// end setUp

    @Test
    public void testInvalidDegreeId(){
        System.out.println("\nStarting Get Degree Courses Test: Invalid degree Id");
        assertEquals("Course list should be empty when degreeId is invalid", 0, degreeReqCourses.getDegreeCourses(-5).size());
        System.out.println("Finished Get Degree Courses Test: Invalid degree Id");
    }

    @Test
    public void testValidDegreeId(){
        System.out.println("\nStarting Get Degree Courses Test: Valid degree Id");
        String[] expectedCourses = {"Introductory Computer Science I", "Introductory Computer Science II"};

        assertEquals("Course missing: Size of course list should be two", 2, degreeReqCourses.getDegreeCourses(1).size());
        assertEquals("First course does not belong to selected degree", expectedCourses[0],degreeReqCourses.getDegreeCourses(1).get(0).getName());
        assertEquals("Second course does not belong to selected degree", expectedCourses[1],degreeReqCourses.getDegreeCourses(1).get(1).getName());
        System.out.println("Finished Get Degree Courses Test: Valid degree Id");
    }
}
