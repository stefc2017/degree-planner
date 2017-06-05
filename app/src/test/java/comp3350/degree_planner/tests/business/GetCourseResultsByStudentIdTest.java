package comp3350.degree_planner.tests.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourseResult;
import comp3350.degree_planner.objects.CourseResult;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Unit tests to get completed courses for a student
 */

public class GetCourseResultsByStudentIdTest extends TestCase {
    private ArrayList<CourseResult> courseResults;
    private AccessCourseResult acr;

    @Before
    public void setUp() {
        Services.createDataAccess(); //?
        acr = new AccessCourseResult();
    }

    @Test
    public void testInvalidStudentId() {
        System.out.println("\nStarting Get Course Results By Student ID Test: invalid student id");

        courseResults = acr.getCourseResultsByStudentId(-1);
        assertTrue("Course results list wasn't empty", courseResults.isEmpty());

        System.out.println("Starting Get Course Results By Student ID Test: invalid student id");
    }

    @Test
    public void testEmptyCourseResults() {
        System.out.println("\nStarting Get Course Results By Student ID Test: valid student id and empty course results");

        courseResults = acr.getCourseResultsByStudentId(2);
        assertTrue("Course results list wasn't empty", courseResults.isEmpty());

        System.out.println("Finished Get Course Results By Student ID Test: valid student id and empty course results");
    }

    @Test
    public void testValid() {
        System.out.println("\nStarting Get Completed Courses Test: valid student id and non-empty course results");

        courseResults = acr.getCourseResultsByStudentId(1);
        assertEquals("ArrayList sizes weren't equal.", 2, courseResults.size());

//        final DataAccessStub dataAccess = new DataAccessStub() {
//            private ArrayList<Course> courses;
//
//            @Override
//            public void open() {
//
//            }
//        };

        System.out.println("Finished Get Completed Courses Test: valid student id and non-empty course results");
    }
}
