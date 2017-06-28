package comp3350.degree_planner.tests.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.persistence.DataAccess;

import static org.junit.Assert.assertEquals;

/**
 * Created by pennyhe on 2017-06-28.
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
    public void testgetCourse() {}

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
