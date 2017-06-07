package comp3350.degree_planner.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.degree_planner.tests.business.GetCompletedCoursesTest;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 * <p>
 * Runs all unit tests
 */

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        System.out.println("Running all tests...\n");
        testBusiness();
        return suite;
    }

    private static void testBusiness() {
        suite.addTestSuite(GetCompletedCoursesTest.class);
    }
}
