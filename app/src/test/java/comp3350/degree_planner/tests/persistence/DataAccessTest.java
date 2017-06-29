package comp3350.degree_planner.tests.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.persistence.DataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by pennyhe on 2017-06-28.
 * Modified by Matthew Provencher on 2017-06-28
 */

public class DataAccessTest {
    private static String dbName = Main.dbName;
    private DataAccess dataAccess;

    @Before
    public void setUp(){
        Services.closeDataAccess();

        System.out.println("\nStart testing Persistence interface with DataAccessStub");
        Services.createDataAccess(new DataAccessStub(dbName));
        dataAccess = Services.getDataAccess();
    }

    @Test
    public void testgetCourse() {
        List<Course> courses = dataAccess.getAllCourses();
        List<Course> noCourses = new ArrayList<Course>();
        CourseResult courseResult = new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5));
        Course result;

        System.out.println("Beginning getCourse tests");

        // Valid courses test
        result = dataAccess.getCourse( courseResult, courses );
        assertNotNull( result );;
        assertEquals( "Introductory Computer Science I", result.getName() );
        result = null;

        // Invalid courses test
        result = dataAccess.getCourse( courseResult, noCourses );
        assertNull( result );

        System.out.println("getCourse tests complete");
    }

    @Test
    public void testGetAllCourses() {
        final int NUM_COURSE = 6;

        List<Course> courses = dataAccess.getAllCourses();
        assertEquals(NUM_COURSE, courses.size());
        // Add more
    }

    @Test
    public void testGetAllDegrees() {}

    @Test
    public void testGetAllPrerequisite() {}

    @Test
    public void testGetAllCourseOfferings() {}

    @Test
    public void testGetCoursesTaken() {}

    @Test
    public void testGetCoursesNotTaken() {}

    @Test
    public void testGetCourseCanTake() {}

    @Test
    public void testGetEligibleRequiredCourse() {}

    @Test
    public void testHasPrerequisite() {}

    @Test
    public void testGetCourseByName() {}

    @Test
    public void testGetDegreeByName() {}

    @Test
    public void testGetCourseById() {}

    @Test
    public void testDegreeById() {}

    @Test
    public void testGetDepartmentById() {}

    @Test
    public void testCourseResultsByStudentId() {}

    @Test
    public void testGetCoursePlanById() {}

    @Test
    public void testGetFailingGradeId() {}

    @Test
    public void testGetCourseOfferingsByTerm() {}

    @Test
    public void testGetDegreeCoursesTaken() {}

    @Test
    public void testGetDegreeCourses() {}

    @Test
    public void testAddToCoursePlan() {}

    @Test
    public void testMoveCourse() {}

    @Test
    public void testRemoveFromCoursePlan() {}

    @After
    public void cleanUp(){
        Services.closeDataAccess();
        System.out.println("Persistence interface tests complete");
    }
}
