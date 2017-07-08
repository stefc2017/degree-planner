package comp3350.degree_planner.tests.integration;

import org.junit.Test;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.tests.persistence.DataAccessTest;

/**
 * Created by Tiffany Jiang on 2017-07-07.
 *
 * Runs persistence tests with real DB
 */

public class DataAccessHSQLDBTest {
    private static String dbName = Main.dbName;

    @Test
    public void testDataAccess() throws Exception
    {
        DataAccess dataAccess;

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        Services.closeDataAccess();
        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess();

        DataAccessTest dataAccessTest = new DataAccessTest();
        dataAccessTest.setDataAccess(dataAccess);

        //30 persistence test methods total
        dataAccessTest.testGetCourse();
        dataAccessTest.testGetAllCourses();
        dataAccessTest.testGetAllDegrees();
        dataAccessTest.testGetAllPrerequisite();
        dataAccessTest.testGetAllCourseOfferings();
        dataAccessTest.testGetCoursesTaken();
        dataAccessTest.testGetCoursesNotTaken();
        dataAccessTest.testGetCourseCanTake();
        dataAccessTest.testGetEligibleRequiredCourse();
        dataAccessTest.testHasPrerequisite();
        dataAccessTest.testGetCourseByName();
        dataAccessTest.testGetDegreeByName();
        dataAccessTest.testGetCourseById();
        dataAccessTest.testDegreeById();
        dataAccessTest.testGetDepartmentById();
        dataAccessTest.testCourseResultsByStudentId();
        dataAccessTest.testGetCoursePlanById();
        dataAccessTest.testGetFailingGradeId();
        dataAccessTest.testGetCourseOfferingsByTerm();
        dataAccessTest.testGetDegreeCoursesTaken();
        dataAccessTest.testGetDegreeCourses();
        dataAccessTest.testIsValidStudentId();
        dataAccessTest.testIsValidCourseId();
        dataAccessTest.testIsValidTermTypeId();
        dataAccessTest.testCourseOffered();
        dataAccessTest.testCoursePlanExists();
        dataAccessTest.testAddToCoursePlan();
        dataAccessTest.testMoveCourse();
        dataAccessTest.testRemoveFromCoursePlan();
        dataAccessTest.testGetCoursePlansByStudentId();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
