package comp3350.degree_planner.tests;

/**
 * Created by Tiffany Jiang on 2017-07-07.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.degree_planner.tests.integration.BusinessPersistenceSeamTest;
import comp3350.degree_planner.tests.integration.DataAccessHSQLDBTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BusinessPersistenceSeamTest.class,
        DataAccessHSQLDBTest.class
})
public class AllIntegrationTests {
}
