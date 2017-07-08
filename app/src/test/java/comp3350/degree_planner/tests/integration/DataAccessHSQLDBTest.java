package comp3350.degree_planner.tests.integration;

import org.junit.Test;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.tests.persistence.DataAccessTest;

/**
 * Created by Tiffany Jiang on 2017-07-07.
 */

public class DataAccessHSQLDBTest {
    private static String dbName = Main.dbName;

    @Test
    public void testDataAccess()
    {
        DataAccess dataAccess;

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        Services.closeDataAccess();
        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess();

        DataAccessTest dataAccessTest = new DataAccessTest();
        dataAccessTest.setDataAccess(dataAccess);

//        dataAccessTest.testGetCourse();
        dataAccessTest.testGetAllCourses();
        dataAccessTest.testGetAllDegrees();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
